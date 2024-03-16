package com.mogo.moviescatalogue.moviedetail.mapper

import com.mogo.domain.model.MovieInfo
import com.mogo.moviescatalogue.common.Constants.IMAGE_BASE_URL
import com.mogo.moviescatalogue.common.model.MovieItem
import javax.inject.Inject

class MovieDetailPresentationMapper @Inject constructor() {
    fun mapDetailDomainModelToPresentationModel(data: MovieInfo) = with(data) {
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