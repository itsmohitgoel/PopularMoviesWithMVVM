package com.mogo.presentation.movielist.mapper

import com.mogo.domain.model.MovieInfo
import com.mogo.presentation.common.Constants.IMAGE_BASE_URL
import com.mogo.presentation.common.model.MovieItem
import javax.inject.Inject

class MovieListPresentationMapper @Inject constructor() {
    fun mapMovieInfoListToMovieItemList(data: List<MovieInfo>) = data.map { movie ->
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