package com.mogo.domain.usecase

import com.mogo.domain.repository.MovieDetailRepository
import com.mogo.domain.utils.Result
import com.mogo.presentation.utils.TestDataGenerators.ERROR_MESSAGE
import com.mogo.presentation.utils.TestDataGenerators.MOVIE_ID
import com.mogo.presentation.utils.TestDataGenerators.MOVIE_TITLE
import com.mogo.presentation.utils.TestDataGenerators.movieInfo
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailUseCaseTest {
    private lateinit var movieDetailUseCase: MovieDetailUseCase
    private val repository: MovieDetailRepository = mockk()

    @Before
    fun setUp() {
        movieDetailUseCase = MovieDetailUseCase(repository)
    }

    @Test
    fun `GIVEN a movies list WHEN specific movie detail is requested THEN movie is returned`() =
        runTest {
            coEvery { repository.fetchMovieDetail(MOVIE_ID) } returns Result.Success(movieInfo)

            val result = movieDetailUseCase.execute(MOVIE_ID) as Result.Success

            Assert.assertNotNull(result)
            assertNotNull(result.data)
            assertEquals(MOVIE_TITLE, result.data.title)
        }

    @Test
    fun `GIVEN a movie list WHEN specific movie is requested But error occurred THEN Error type is returned`() =
        runTest {
            coEvery { repository.fetchMovieDetail(MOVIE_ID) } returns Result.Error(message = ERROR_MESSAGE)

            val result = movieDetailUseCase.execute(MOVIE_ID) as Result.Error

            assertNotNull(result)
            Assert.assertEquals(ERROR_MESSAGE, result.message)
        }

}