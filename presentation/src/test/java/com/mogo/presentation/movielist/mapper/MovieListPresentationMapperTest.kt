package com.mogo.presentation.movielist.mapper

import com.mogo.presentation.utils.TestDataGenerators.MOVIE_ID
import com.mogo.presentation.utils.TestDataGenerators.MOVIE_TITLE
import com.mogo.presentation.utils.TestDataGenerators.movieInfoList
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class MovieListPresentationMapperTest {

    @Test
    fun `GIVEN a movieInfo list WHEN the mapping of movieItem is requested THEN movieItem list is returned`() =
        runTest {
            val mapper = MovieListPresentationMapper()

            val movies = mapper.mapMovieInfoListToMovieItemList(movieInfoList)

            Assert.assertEquals(1, movies.size)
            Assert.assertEquals(MOVIE_ID, movies.first().movieId)
            Assert.assertEquals(MOVIE_TITLE, movies.first().title)
        }

}