package com.mogo.data.repo

import com.mogo.data.mapper.MovieListMapper
import com.mogo.data.model.MovieDto
import com.mogo.data.remote.NetworkService
import com.mogo.data.repository.MovieListRepositoryImpl
import com.mogo.domain.utils.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieListRepositoryImplTest {
    private lateinit var repoImpl: MovieListRepositoryImpl
    private lateinit var networkService: NetworkService
    private lateinit var listMapper: MovieListMapper

    @Before
    fun setUp() {
        networkService = mockk()
        listMapper = MovieListMapper()
        repoImpl = MovieListRepositoryImpl(networkService, listMapper)
    }

    @Test
    fun `Given movie list WHEN fetchMovies repo's method is invoked THEN Result with movie list is returned  `() =
        runTest {
            coEvery { networkService.getMoviesResponse().moviesList } returns listOf(movie)

            val result = repoImpl.fetchMovies() as Result.Success

            assertNotNull(result)
            assertNotNull(result.data)
            assertEquals(MOVIE_TITLE, result.data.first().title)
        }

    @Test
    fun `Given movie list WHEN fetchMovies repo's method is invoked THEN Result with error is returned  `() =
        runTest {
            coEvery { networkService.getMoviesResponse().moviesList } throws java.lang.RuntimeException()

            val result = repoImpl.fetchMovies() as Result.Error

            assertNotNull(result.message)
            assertEquals(ERROR_MESSAGE, result.message)
        }

    private companion object {
        const val MOVIE_ID = 1234
        const val MOVIE_TITLE = "No Way Up"
        const val ERROR_MESSAGE = "Error: Something went wrong"

        val movie = MovieDto(
            movieId = MOVIE_ID,
            title = MOVIE_TITLE,
            posterPath = "/hu40Uxp9WtpL34jv3zyWLb5zEVY.jpg",
            backdropPath = "/mDeUmPe4MF35WWlAqj4QFX5UauJ.jpg",
            summary = "Characters from different backgrounds are thrown together when the plane they\u0027re travelling on crashes into the Pacific Ocean. A nightmare fight for survival ensues with the air supply running out and dangers creeping in from all sides.",
            rating = 6.073,
            popularity = 1709.062,
            releaseDate = "2024-01-18",
            tagline = "Default: Tag line",
            adult = false,
            originalLanguage = "En",
            originalTitle = "Dummy Title",
            video = false,
            voteCount = 0
        )
    }

}