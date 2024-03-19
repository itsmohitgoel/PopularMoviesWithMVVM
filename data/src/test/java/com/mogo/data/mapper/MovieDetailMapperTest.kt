package com.mogo.data.mapper

import com.mogo.data.model.MovieDto
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class MovieDetailMapperTest {

    @Test
    fun `GIVEN a movie Dto list WHEN the mapping of movies is requested THEN movie info list is returned`() =
        runTest {
            val mapper = MovieDetailMapper()

            val movies = mapper.mapDetailDtoToDomainModel(moviesDto)

            Assert.assertEquals(MOVIE_ID, movies.movieId)
            Assert.assertEquals(MOVIE_TITLE, movies.title)
        }

    private companion object {
        const val MOVIE_ID = 1234
        const val MOVIE_TITLE = "No Way Up"

        val moviesDto = MovieDto(
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