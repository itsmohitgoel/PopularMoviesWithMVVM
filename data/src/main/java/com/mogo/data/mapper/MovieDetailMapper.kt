package com.mogo.data.mapper

import com.mogo.data.Constants.DEFAULT_TAGLINE
import com.mogo.data.model.MovieDto
import com.mogo.domain.model.MovieInfo
import javax.inject.Inject

class MovieDetailMapper @Inject constructor() {
    fun mapDetailDtoToDomainModel(data: MovieDto) = with(data) {
        MovieInfo(
            movieId = movieId,
            title = title,
            posterPath = posterPath,
            backdropPath = backdropPath,
            summary = summary,
            rating = rating,
            popularity = popularity,
            releaseDate = releaseDate,
            tagline = tagline ?: DEFAULT_TAGLINE
        )
    }
}