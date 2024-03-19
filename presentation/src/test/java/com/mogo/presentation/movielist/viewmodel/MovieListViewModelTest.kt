package com.mogo.presentation.movielist.viewmodel

import com.mogo.domain.model.MovieInfo
import com.mogo.domain.usecase.MovieListUseCase
import com.mogo.domain.utils.Result
import com.mogo.presentation.MainDispatcherRule
import com.mogo.presentation.common.model.MovieItem
import com.mogo.presentation.movielist.MovieListAction
import com.mogo.presentation.movielist.mapper.MovieListPresentationMapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieListViewModelTest {
    @get:Rule
    val manDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: MovieListViewModel
    private lateinit var useCase: MovieListUseCase
    private lateinit var mapper: MovieListPresentationMapper
    private lateinit var actionFlow: MutableSharedFlow<MovieListAction>


    @Before
    fun setUp() {
        useCase = mockk()
        mapper = mockk()
        actionFlow = mockk()

        viewModel = MovieListViewModel(useCase, mapper)
    }

    @Test
    fun `GIVEN movie list WHEN movies are requested with Loading Action THEN loading state is returned`() {
        assertTrue(viewModel.viewStateFlow.value.loading)
    }

    @Test
    fun `GIVEN movie list WHEN movies are requested THEN movies are returned`() {
        // init
        coEvery { useCase.execute() } returns Result.Success(movieInfoList)
        coEvery { mapper.mapMovieInfoListToMovieItemList(movieInfoList) } returns movieItemList


        //act
        runTest {
            viewModel.submitAction(MovieListAction.LoadMovieList)
            advanceUntilIdle()
        }

        //assert
        assertTrue(viewModel.viewStateFlow.value.movies.isNotEmpty())
    }

    @Test
    fun `GIVEN movie list WHEN movies are requested but error occurred THEN error is returned`() {
        coEvery { useCase.execute() } returns Result.Error(ERROR_MESSAGE)

        runTest {
            viewModel.submitAction(MovieListAction.LoadMovieList)
        }

        assertTrue(viewModel.viewStateFlow.value.error == ERROR_MESSAGE)
    }

    private companion object {
        const val MOVIE_ID = 1234
        const val MOVIE_TITLE = "No Way Up"
        const val ERROR_MESSAGE = "Error: Something went wrong"

        val movieInfo = MovieInfo(
            movieId = MOVIE_ID,
            title = MOVIE_TITLE,
            posterPath = "/hu40Uxp9WtpL34jv3zyWLb5zEVY.jpg",
            backdropPath = "/mDeUmPe4MF35WWlAqj4QFX5UauJ.jpg",
            summary = "Characters from different backgrounds are thrown together when the plane they\u0027re travelling on crashes into the Pacific Ocean. A nightmare fight for survival ensues with the air supply running out and dangers creeping in from all sides.",
            rating = 6.073,
            popularity = 1709.062,
            releaseDate = "2024-01-18",
            tagline = "Default: Tag line"
        )

        val movieItem = MovieItem(
            movieId = MOVIE_ID,
            title = MOVIE_TITLE,
            backdropPath = "/mDeUmPe4MF35WWlAqj4QFX5UauJ.jpg",
            summary = "Characters from different backgrounds are thrown together when the plane they\u0027re travelling on crashes into the Pacific Ocean. A nightmare fight for survival ensues with the air supply running out and dangers creeping in from all sides.",
            rating = 6.073,
            popularity = 1709.062,
            releaseDate = "2024-01-18",
            tagline = "Default: Tag line"
        )

        val movieInfoList = listOf(movieInfo)
        val movieItemList = listOf(movieItem)
    }
}