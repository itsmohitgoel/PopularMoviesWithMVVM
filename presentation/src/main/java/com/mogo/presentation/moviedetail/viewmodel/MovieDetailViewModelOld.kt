package com.mogo.presentation.moviedetail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mogo.domain.usecase.MovieDetailUseCase
import com.mogo.presentation.moviedetail.mapper.MovieDetailPresentationMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModelOld @Inject constructor(
    private val usecase: MovieDetailUseCase,
    private val mapper: MovieDetailPresentationMapper,
    private val dispatcher: CoroutineDispatcher,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

//    private val _state = MutableStateFlow(MovieDetailState(isLoading = true))
//    val state: StateFlow<MovieDetailState> = _state.asStateFlow()

    /**
     * This function get the Movie's Detail from Repository via MovieDetailUseCase
     */
    suspend fun getMovieDetail() {
//        savedStateHandle.get<String>(PARAM_MOVIE_ID)?.let { movieId ->
//
//            viewModelScope.launch(dispatcher) {
//                val result = usecase.execute(movieId.toInt())
//                when (result) {
//                    is Result.Success -> {
//                        _state.value =
//                            MovieDetailState(
//                                movieItem =
//                                mapper.mapDetailDomainModelToPresentationModel(result.data)
//                            )
//                    }
//
//                    is Result.Error -> {
//                        _state.value =
//                            MovieDetailState(error = result.message )
//                    }
//
//                    is Result.Loading -> {
//                        _state.value = MovieDetailState(isLoading = true)
//                    }
//                }
//
//            }
//        }

        //TODO : clear viewModelScope
    }
}