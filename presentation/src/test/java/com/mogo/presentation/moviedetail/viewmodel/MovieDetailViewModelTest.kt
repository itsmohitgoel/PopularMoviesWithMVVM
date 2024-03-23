package com.mogo.presentation.moviedetail.viewmodel

import com.mogo.domain.usecase.MovieDetailUseCase
import com.mogo.domain.utils.Result
import com.mogo.presentation.MainDispatcherRule
import com.mogo.presentation.moviedetail.MovieDetailAction
import com.mogo.presentation.moviedetail.mapper.MovieDetailPresentationMapper
import com.mogo.presentation.utils.TestDataGenerators.ERROR_MESSAGE
import com.mogo.presentation.utils.TestDataGenerators.MOVIE_ID
import com.mogo.presentation.utils.TestDataGenerators.MOVIE_TITLE
import com.mogo.presentation.utils.TestDataGenerators.movieInfo
import com.mogo.presentation.utils.TestDataGenerators.movieItem
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
}