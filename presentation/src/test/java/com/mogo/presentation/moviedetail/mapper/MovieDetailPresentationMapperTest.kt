package com.mogo.presentation.moviedetail.mapper

import com.mogo.domain.model.MovieInfo
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
    }
}