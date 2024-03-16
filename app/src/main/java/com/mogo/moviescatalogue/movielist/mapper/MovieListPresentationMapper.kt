package com.mogo.moviescatalogue.movielist.mapper

import com.mogo.domain.model.MovieInfo
import com.mogo.moviescatalogue.common.Constants.IMAGE_BASE_URL
import com.mogo.moviescatalogue.common.model.MovieItem
import javax.inject.Inject

class MovieListPresentationMapper @Inject constructor() {
    fun mapListDomainModelToPresentationModel(data: List<MovieInfo>) = data.map { movie ->
        with(movie) {
            MovieItem(
                movieId = movieId,
                title = title,
                summary = summary,
                rating = rating,
                popularity = popularity,
                imageUrl = IMAGE_BASE_URL + posterPath
            )
        }
    }
}