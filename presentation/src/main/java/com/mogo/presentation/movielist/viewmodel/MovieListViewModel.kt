package com.mogo.presentation.movielist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mogo.domain.usecase.MovieListUseCase
import com.mogo.domain.utils.Result
import com.mogo.presentation.movielist.MovieListAction
import com.mogo.presentation.movielist.MovieListOneTimeEvent
import com.mogo.presentation.movielist.MovieListViewState
import com.mogo.presentation.movielist.mapper.MovieListPresentationMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val useCase: MovieListUseCase,
    private val mapper: MovieListPresentationMapper
) : ViewModel() {
    private val _viewStateFlow: MutableStateFlow<MovieListViewState> by lazy {
        MutableStateFlow(MovieListViewState(loading = true))
    }
    val viewStateFlow: StateFlow<MovieListViewState> = _viewStateFlow

    private val actionFlow: MutableSharedFlow<MovieListAction> = MutableSharedFlow()

    private val _oneTimeEventChannel: Channel<MovieListOneTimeEvent> = Channel()
    val oneTimeEventChannelFlow: Flow<MovieListOneTimeEvent> =
        _oneTimeEventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            actionFlow.collect { action ->
                handleAction(action)
            } /// todo : move
        }
    }


    private fun handleAction(action: MovieListAction) {
        when (action) {
            is MovieListAction.LoadMovieList -> viewModelScope.launch(Dispatchers.IO) {
                // Fetch UI State
                val state: MovieListViewState = fetchMovieListState()
                submitState(state)
            }

            is MovieListAction.MovieListItemClick -> {
                submitOneTimeEvent(MovieListOneTimeEvent.NavigateToDetailScreen(action.movieId))
            }
        }
    }

    private suspend fun fetchMovieListState(): MovieListViewState {
        val result = useCase.execute()
        val state = when (result) {
            is Result.Success -> {
                MovieListViewState(
                    movies = mapper.mapMovieInfoListToMovieItemList(
                        result.data
                    )
                )
            }

            is Result.Error -> MovieListViewState(error = result.message)
        }
        return state
    }

    private fun submitState(state: MovieListViewState) {
        _viewStateFlow.value = state
    }

    fun submitAction(action: MovieListAction) {
        viewModelScope.launch {
            actionFlow.emit(action)
        }
    }

    fun submitOneTimeEvent(oneTimeEvent: MovieListOneTimeEvent) {
        viewModelScope.launch {
            _oneTimeEventChannel.send(oneTimeEvent)
        }
    }
}