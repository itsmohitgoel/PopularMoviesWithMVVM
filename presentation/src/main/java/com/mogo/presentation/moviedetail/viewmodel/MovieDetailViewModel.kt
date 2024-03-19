package com.mogo.presentation.moviedetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mogo.domain.model.MovieInfo
import com.mogo.domain.usecase.MovieDetailUseCase
import com.mogo.domain.utils.Result
import com.mogo.presentation.common.model.MovieItem
import com.mogo.presentation.moviedetail.MovieDetailAction
import com.mogo.presentation.moviedetail.MovieDetailOneTimeEvent
import com.mogo.presentation.moviedetail.MovieDetailViewState
import com.mogo.presentation.moviedetail.mapper.MovieDetailPresentationMapper
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
class MovieDetailViewModel @Inject constructor(
    private val usecase: MovieDetailUseCase,
    private val mapper: MovieDetailPresentationMapper
) : ViewModel() {
    private val _viewStateFlow: MutableStateFlow<MovieDetailViewState> by lazy {
        MutableStateFlow(MovieDetailViewState(loading = true))
    }
    val viewStateFlow: StateFlow<MovieDetailViewState> = _viewStateFlow

    private val actionFlow: MutableSharedFlow<MovieDetailAction> =
        MutableSharedFlow()

    private val _oneTimeEventChannel: Channel<MovieDetailOneTimeEvent> = Channel()
    val oneTimeEventChanneFlow: Flow<MovieDetailOneTimeEvent> =
        _oneTimeEventChannel.receiveAsFlow()


    init {
        viewModelScope.launch {
            actionFlow.collect { action ->
                handleAction(action)
            }
        }
    }

    private fun handleAction(action: MovieDetailAction) {
        when (action) {
            is MovieDetailAction.LoadMovieDetail -> viewModelScope.launch(Dispatchers.IO) {
                val state: MovieDetailViewState = fetchMovieDetailState(action.movieId)
                submitState(state)
            }
        }
    }

    private suspend fun fetchMovieDetailState(movieId: Int): MovieDetailViewState {
        val result: Result<MovieInfo> = usecase.execute(movieId)
        val state = when (result) {
            is Result.Error -> MovieDetailViewState(movie = MovieItem(), error = result.message)
            is Result.Success -> MovieDetailViewState(movie = mapper.mapMovieInfoToMovieItem(result.data))
        }

        return state
    }

    fun submitAction(action: MovieDetailAction) {
        viewModelScope.launch(Dispatchers.Default) {
            actionFlow.emit(action)
        }
    }

    private fun submitState(state: MovieDetailViewState) {
        _viewStateFlow.value = state
    }

    fun submitOneTimeEvent(oneTimeEvent: MovieDetailOneTimeEvent) {
        viewModelScope.launch(Dispatchers.Default) {
            _oneTimeEventChannel.send(oneTimeEvent)
        }
    }
}