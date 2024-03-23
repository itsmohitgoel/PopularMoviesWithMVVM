package com.mogo.data.repo

import com.mogo.data.mapper.MovieDetailMapper
import com.mogo.data.remote.NetworkService
import com.mogo.data.repository.MovieDetailRepositoryImpl
import com.mogo.domain.utils.Result
import com.mogo.presentation.utils.TestDataGenerators.ERROR_MESSAGE
import com.mogo.presentation.utils.TestDataGenerators.MOVIE_ID
import com.mogo.presentation.utils.TestDataGenerators.MOVIE_TITLE
import com.mogo.presentation.utils.TestDataGenerators.movieDto
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailRepositoryImplTest {
    private lateinit var repoImpl: MovieDetailRepositoryImpl
    private lateinit var networkService: NetworkService
    private lateinit var detailMapper: MovieDetailMapper

    @Before
    fun setUp() {
        networkService = mockk()
        detailMapper = MovieDetailMapper()
        repoImpl = MovieDetailRepositoryImpl(networkService, detailMapper)
    }

    @Test
    fun `Given movie list WHEN specific movie detail is requested THEN Result with single movie is returned  `() =
        runTest {
            coEvery { networkService.getMovieDetail(MOVIE_ID) } returns movieDto

            val result = repoImpl.fetchMovieDetail(MOVIE_ID) as Result.Success

            assertNotNull(result)
            assertNotNull(result.data)
            assertEquals(MOVIE_TITLE, result.data.title)
        }

    @Test
    fun `Given movie list WHEN specific movie detail is requested THEN Result with error is returned  `() =
        runTest {
            coEvery { networkService.getMovieDetail(MOVIE_ID) } throws java.lang.RuntimeException()

            val result = repoImpl.fetchMovieDetail(MOVIE_ID) as Result.Error

            assertNotNull(result.message)
            assertEquals(ERROR_MESSAGE, result.message)
        }
}