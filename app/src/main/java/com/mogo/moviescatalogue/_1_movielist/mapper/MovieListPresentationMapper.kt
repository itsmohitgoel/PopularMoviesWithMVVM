package com.mogo.moviescatalogue._1_movielist.mapper

import com.mogo.common.utils.Mapper
import com.mogo.domain.model.Movie
import com.mogo.moviescatalogue.common.Constants.IMAGE_BASE_URL
import com.mogo.moviescatalogue.common.model.MovieItem
import javax.inject.Inject

class MovieListPresentationMapper @Inject constructor() :
    Mapper<List<Movie>, List<MovieItem>> {

    override fun map(data: List<Movie>) = data.map { movie ->
        with(movie) {
            MovieItem(
                movieId = movieId,
                title = title,
                summary = summary,
                rating = rating,
                popularity = popularity,
                imageUrl = IMAGE_BASE_URL+posterPath
            )
        }
    }
}