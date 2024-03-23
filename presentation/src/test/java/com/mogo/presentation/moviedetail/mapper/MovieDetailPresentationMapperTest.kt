package com.mogo.presentation.moviedetail.mapper

import com.mogo.presentation.utils.TestDataGenerators.MOVIE_ID
import com.mogo.presentation.utils.TestDataGenerators.MOVIE_TITLE
import com.mogo.presentation.utils.TestDataGenerators.movieInfo
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class MovieDetailPresentationMapperTest {

    @Test
    fun `GIVEN a MovieInfo instance WHEN the mapping to MovieItem is requested THEN MovieItem is returned`() =
        runTest {
            val mapper = MovieDetailPresentationMapper()

            val movies = mapper.mapMovieInfoToMovieItem(movieInfo)

            Assert.assertEquals(MOVIE_ID, movies.movieId)
            Assert.assertEquals(MOVIE_TITLE, movies.title)
        }


}