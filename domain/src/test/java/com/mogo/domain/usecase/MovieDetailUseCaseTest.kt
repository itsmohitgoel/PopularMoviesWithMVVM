package com.mogo.domain.usecase

import com.mogo.domain.model.MovieInfo
import com.mogo.domain.repository.MovieDetailRepository
import com.mogo.domain.utils.Result
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
            coEvery { repository.fetchMovieDetail(MOVIE_ID) } returns Result.Success(movie)

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

    private companion object {
        const val MOVIE_ID = 1234
        const val MOVIE_TITLE = "No Way Up"
        const val ERROR_MESSAGE = "Something went wrong"

        val movie = MovieInfo(
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
    }
}