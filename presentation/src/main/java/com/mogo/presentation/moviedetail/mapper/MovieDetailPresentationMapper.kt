package com.mogo.presentation.moviedetail.mapper

import com.mogo.domain.model.MovieInfo
import com.mogo.presentation.common.UIConstants.IMAGE_BASE_URL
import com.mogo.presentation.common.model.MovieItem
import javax.inject.Inject

class MovieDetailPresentationMapper @Inject constructor() {
    fun mapMovieInfoToMovieItem(data: MovieInfo) = with(data) {
        MovieItem(
            movieId = movieId,
            title = title,
            summary = summary,
            rating = rating,
            popularity = popularity,
            releaseDate = releaseDate,
            imageUrl = IMAGE_BASE_URL + backdropPath,
            tagline = tagline
        )

    }
}