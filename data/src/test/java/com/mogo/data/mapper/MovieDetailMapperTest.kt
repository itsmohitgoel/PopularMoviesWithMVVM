package com.mogo.data.mapper

import com.mogo.presentation.utils.TestDataGenerators.MOVIE_ID
import com.mogo.presentation.utils.TestDataGenerators.MOVIE_TITLE
import com.mogo.presentation.utils.TestDataGenerators.movieDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class MovieDetailMapperTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `GIVEN a movie Dto list WHEN the mapping of movies is requested THEN movie info list is returned`() =
        runTest {
            val mapper = MovieDetailMapper()

            val movies = mapper.mapDetailDtoToDomainModel(movieDto)

            Assert.assertEquals(MOVIE_ID, movies.movieId)
            Assert.assertEquals(MOVIE_TITLE, movies.title)
        }
}
