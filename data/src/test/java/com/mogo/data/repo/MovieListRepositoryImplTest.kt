package com.mogo.data.repo

import com.mogo.data.mapper.MovieListMapper
import com.mogo.data.remote.NetworkService
import com.mogo.data.repository.MovieListRepositoryImpl
import com.mogo.domain.utils.Result
import com.mogo.presentation.utils.TestDataGenerators.ERROR_MESSAGE
import com.mogo.presentation.utils.TestDataGenerators.MOVIE_TITLE
import com.mogo.presentation.utils.TestDataGenerators.movieDtoList
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
            coEvery { networkService.getMoviesResponse().moviesList } returns movieDtoList

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
}