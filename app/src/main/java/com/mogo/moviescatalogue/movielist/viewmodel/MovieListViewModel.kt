package com.mogo.moviescatalogue.movielist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mogo.domain.usecase.MovieListUseCase
import com.mogo.domain.utils.Result
import com.mogo.moviescatalogue.movielist.MovieListState
import com.mogo.moviescatalogue.movielist.mapper.MovieListPresentationMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val usecase: MovieListUseCase,
    private val mapper: MovieListPresentationMapper,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(MovieListState(isLoading = true))
    val state: StateFlow<MovieListState> = _state.asStateFlow()

    /**
     * This function get the Movie List from Repository via MovieListUseCase
     */
    suspend fun getMoviesList() {
        viewModelScope.launch(dispatcher) {
            val result = usecase.execute()
            when (result) {
                is Result.Success -> {
                    _state.value =
                        MovieListState(
                            movieList =
                            mapper.mapListDomainModelToPresentationModel(result.data)
                        )
                }

                is Result.Error -> {
                    _state.value =
                        MovieListState(error = result.message)
                }

                is Result.Loading -> {
                    _state.value = MovieListState(isLoading = true)
                }
            }

        }
    }
}