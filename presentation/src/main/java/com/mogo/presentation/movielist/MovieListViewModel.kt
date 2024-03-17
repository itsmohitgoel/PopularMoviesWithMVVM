package com.mogo.presentation.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mogo.domain.usecase.MovieListUseCase
import com.mogo.domain.utils.Result
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
    private val _viewStateFlow: MutableStateFlow<MovieListState> by lazy {
        MutableStateFlow(MovieListState(loading = true))
    }
    val viewStateFlow: StateFlow<MovieListState> = _viewStateFlow

    private val actionFlow: MutableSharedFlow<MovieListAction> = MutableSharedFlow()

    private val _oneTimeEventChannel: Channel<MovieListOneTimeEvent> = Channel()
    val oneTimeEventChannelFlow: Flow<MovieListOneTimeEvent> =
        _oneTimeEventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            actionFlow.collect { action ->
                handleAction(action)
            }
        }
    }


    private fun handleAction(action: MovieListAction) {
        when (action) {
            is MovieListAction.LoadMovieList -> viewModelScope.launch(Dispatchers.IO) {
                // Fetch UI State
                val state: MovieListState = fetchMovieListState()
                submitState(state)
            }

            is MovieListAction.MovieListItemClick -> {
                submitOneTimeEvent(MovieListOneTimeEvent.NavigateToDetailScreen(action.movieId))
            }
        }
    }

    private suspend fun fetchMovieListState(): MovieListState {
        val result = useCase.execute()
        val state = when (result) {
            is Result.Success -> {
                MovieListState(
                    movies = mapper.mapMovieInfoListToMovieItemList(
                        result.data
                    )
                )
            }

            is Result.Error -> MovieListState(error = result.message)
        }
        return state
    }

    private fun submitState(state: MovieListState) {
        _viewStateFlow.value = state
    }

    fun submitAction(action: MovieListAction) {
        viewModelScope.launch {
            actionFlow.emit(action)
        }
    }

    fun submitOneTimeEvent(oneTimeEvent: MovieListOneTimeEvent) {
        viewModelScope.launch(Dispatchers.Default) {
            _oneTimeEventChannel.send(oneTimeEvent)
        }
    }
}