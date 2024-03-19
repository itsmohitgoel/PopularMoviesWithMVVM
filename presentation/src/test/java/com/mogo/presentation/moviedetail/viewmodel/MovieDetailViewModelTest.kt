package com.mogo.presentation.moviedetail.viewmodel

import com.mogo.domain.model.MovieInfo
import com.mogo.domain.usecase.MovieDetailUseCase
import com.mogo.domain.utils.Result
import com.mogo.presentation.MainDispatcherRule
import com.mogo.presentation.common.model.MovieItem
import com.mogo.presentation.moviedetail.MovieDetailAction
import com.mogo.presentation.moviedetail.mapper.MovieDetailPresentationMapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailViewModelTest {
    @get:Rule
    val manDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var useCase: MovieDetailUseCase
    private lateinit var mapper: MovieDetailPresentationMapper
    private lateinit var actionFlow: MutableSharedFlow<MovieDetailAction>


    @Before
    fun setUp() {
        useCase = mockk()
        mapper = mockk()
        actionFlow = mockk()

        viewModel = MovieDetailViewModel(useCase, mapper)
    }

    @Test
    fun `GIVEN movie list WHEN specific movie is requested with Loading Action THEN loading state is returned`() {
        Assert.assertTrue(viewModel.viewStateFlow.value.loading)
    }

    @Test
    fun `GIVEN movie list WHEN specific movie is requested THEN respective movie is returned`() {
        // init
        coEvery { useCase.execute(MOVIE_ID) } returns Result.Success(movieInfo)
        coEvery { mapper.mapMovieInfoToMovieItem(movieInfo) } returns movieItem


        //act
        runTest {
            viewModel.submitAction(MovieDetailAction.LoadMovieDetail(MOVIE_ID))
            advanceUntilIdle()
        }

        //assert
        Assert.assertEquals(MOVIE_ID, viewModel.viewStateFlow.value.movie.movieId)
        Assert.assertEquals(MOVIE_TITLE, viewModel.viewStateFlow.value.movie.title)
    }

    @Test
    fun `GIVEN movie list WHEN specific movie is requested but error occurred THEN error state is returned`() {
        coEvery { useCase.execute(MOVIE_ID) } returns Result.Error(ERROR_MESSAGE)

        runTest {
            viewModel.submitAction(MovieDetailAction.LoadMovieDetail(MOVIE_ID))
        }

        Assert.assertTrue(viewModel.viewStateFlow.value.error == ERROR_MESSAGE)
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
    }
}