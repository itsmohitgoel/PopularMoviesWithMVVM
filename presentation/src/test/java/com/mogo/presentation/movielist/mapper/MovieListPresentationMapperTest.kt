package com.mogo.presentation.movielist.mapper

import com.mogo.domain.model.MovieInfo
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

    private companion object {
        const val MOVIE_ID = 1234
        const val MOVIE_TITLE = "No Way Up"

        val movieInfo = MovieInfo(
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

        val movieInfoList = listOf(movieInfo)
    }
}