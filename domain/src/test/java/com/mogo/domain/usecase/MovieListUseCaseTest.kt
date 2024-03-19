package com.mogo.domain.usecase

import com.mogo.domain.model.MovieInfo
import com.mogo.domain.repository.MovieListRepository
import com.mogo.domain.utils.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.*
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
            coEvery { repository.fetchMovies() } returns Result.Success(listOf(movies))

            val result = movieListUseCase.execute() as Result.Success

            assertNotNull(result.data)
            assertEquals(1, result.data.size)
            assertEquals(MOVIE_ID, result.data.first().movieId)
            assertEquals(MOVIE_TITLE, result.data.first().title)
        }

    @Test
    fun `GIVEN a list WHEN movie list is requested But error occurred THEN Error type is returned`() =
        runTest {
            coEvery {repository.fetchMovies() }returns  Result.Error(message = ERROR_MESSAGE )

            val result = movieListUseCase.execute() as Result.Error

            assertEquals(ERROR_MESSAGE, result.message)
        }

    private companion object {
        const val MOVIE_ID = 1234
        const val MOVIE_TITLE = "No Way Up"
        const val ERROR_MESSAGE = "Something went wrong"

        val movies = MovieInfo(
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