package com.mogo.domain.usecase

import com.mogo.domain.repository.MovieListRepository
import com.mogo.domain.utils.Result
import com.mogo.presentation.utils.TestDataGenerators.ERROR_MESSAGE
import com.mogo.presentation.utils.TestDataGenerators.MOVIE_ID
import com.mogo.presentation.utils.TestDataGenerators.MOVIE_TITLE
import com.mogo.presentation.utils.TestDataGenerators.movieInfoList
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieListUseCaseTest {
    private lateinit var movieListUseCase: MovieListUseCase
    private val repository: MovieListRepository = mockk()


    @Before
    fun setUp() {
        movieListUseCase = MovieListUseCase(repository)
    }

    @Test
    fun `GIVEN a list WHEN movie list is requested THEN movies are returned`() =
        runTest {
            coEvery { repository.fetchMovies() } returns Result.Success(movieInfoList)

            val result = movieListUseCase.execute() as Result.Success

            assertNotNull(result.data)
            assertEquals(1, result.data.size)
            assertEquals(MOVIE_ID, result.data.first().movieId)
            assertEquals(MOVIE_TITLE, result.data.first().title)
        }

    @Test
    fun `GIVEN a list WHEN movie list is requested But error occurred THEN Error type is returned`() =
        runTest {
            coEvery { repository.fetchMovies() } returns Result.Error(message = ERROR_MESSAGE)

            val result = movieListUseCase.execute() as Result.Error

            assertEquals(ERROR_MESSAGE, result.message)
        }

}