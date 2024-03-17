package com.mogo.moviescatalogue.movielist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mogo.domain.usecase.MovieListUseCase
import com.mogo.domain.utils.Result
import com.mogo.moviescatalogue.movielist.MovieListState_Old
import com.mogo.presentation.movielist.mapper.MovieListPresentationMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel_Old @Inject constructor(
    private val useCase: MovieListUseCase,
    private val mapper: MovieListPresentationMapper,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(MovieListState_Old(isLoading = true))
    val state: StateFlow<MovieListState_Old> = _state.asStateFlow()

    /**
     * This function get the Movie List from Repository via MovieListUseCase
     */
    suspend fun getMoviesList() {
        viewModelScope.launch(dispatcher) {
            val result = useCase.execute()
            when (result) {
                is Result.Success -> {
                    _state.value =
                        MovieListState_Old(
                            movies =
                            mapper.mapMovieInfoListToMovieItemList(result.data)
                        )
                }

                is Result.Error -> {
                    _state.value =
                        MovieListState_Old(error = result.message)
                }

//                is Result.Loading -> {
//                    _state.value = MovieListState_Old(isLoading = true)
//                }
            }

        }
    }
}