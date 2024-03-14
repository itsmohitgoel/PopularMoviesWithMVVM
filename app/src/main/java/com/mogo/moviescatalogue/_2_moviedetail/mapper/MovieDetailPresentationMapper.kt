package com.mogo.moviescatalogue._2_moviedetail.mapper

import com.mogo.common.utils.Mapper
import com.mogo.domain.model.Movie
import com.mogo.moviescatalogue.common.Constants.IMAGE_BASE_URL
import com.mogo.moviescatalogue.common.model.MovieItem
import javax.inject.Inject

class MovieDetailPresentationMapper @Inject constructor() : Mapper<Movie, MovieItem> {

    override fun map(data: Movie) = with(data) {
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