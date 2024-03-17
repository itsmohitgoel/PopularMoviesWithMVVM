package com.mogo.presentation.moviedetail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mogo.domain.model.MovieInfo
import com.mogo.domain.usecase.MovieDetailUseCase
import com.mogo.domain.utils.Result
import com.mogo.presentation.common.model.MovieItem
import com.mogo.presentation.moviedetail.MovieDetailAction
import com.mogo.presentation.moviedetail.MovieDetailOneTimeEvent
import com.mogo.presentation.moviedetail.MovieDetailState
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
    private val mapper: MovieDetailPresentationMapper,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _viewStateFlow: MutableStateFlow<MovieDetailState> by lazy {
        MutableStateFlow(MovieDetailState(loading = true))
    }
    val viewStateFlow: StateFlow<MovieDetailState> = _viewStateFlow

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
                val state: MovieDetailState = fetchMovieDetailState(action.movieId)
                submitState(state)
            }
        }
    }

    private suspend fun fetchMovieDetailState(movieId: Int): MovieDetailState {
        val result: Result<MovieInfo> = usecase.execute(movieId)
        val state = when (result) {
            is Result.Error -> MovieDetailState(movie = MovieItem(), error = result.message)
            is Result.Success -> MovieDetailState(movie = mapper.mapMovieInfoToMovieItem(result.data))
        }

        return state
    }

    fun submitAction(action: MovieDetailAction) {
        viewModelScope.launch(Dispatchers.Default) {
            actionFlow.emit(action)
        }
    }

    private fun submitState(state: MovieDetailState) {
        _viewStateFlow.value = state
    }

    fun submitOneTimeEvent(oneTimeEvent: MovieDetailOneTimeEvent) {
        viewModelScope.launch(Dispatchers.Default) {
            _oneTimeEventChannel.send(oneTimeEvent)
        }
    }
}