package com.mogo.presentation.utils

import com.mogo.domain.model.MovieInfo
import com.mogo.presentation.common.model.MovieItem

object TestDataGenerators {
    const val MOVIE_ID = 1234
    const val MOVIE_TITLE = "No Way Up"
    const val ERROR_MESSAGE = "Error: Something went wrong"

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

    val movieItem = MovieItem(
        movieId = MOVIE_ID,
        title = MOVIE_TITLE,
        backdropPath = "/mDeUmPe4MF35WWlAqj4QFX5UauJ.jpg",
        summary = "Characters from different backgrounds are thrown together when the plane they\u0027re travelling on crashes into the Pacific Ocean. A nightmare fight for survival ensues with the air supply running out and dangers creeping in from all sides.",
        rating = 6.073,
        popularity = 1709.062,
        releaseDate = "2024-01-18",
        tagline = "Default: Tag line"
    )

    val movieInfoList = listOf(movieInfo)
    val movieItemList = listOf(movieItem)

}