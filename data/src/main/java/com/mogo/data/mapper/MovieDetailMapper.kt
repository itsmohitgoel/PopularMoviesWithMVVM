package com.mogo.data.mapper

import com.mogo.common.utils.Mapper
import com.mogo.data.model.MovieDTO
import com.mogo.domain.model.Movie
import javax.inject.Inject

class MovieDetailMapper @Inject constructor():
    Mapper<MovieDTO, Movie> {

    override fun map(data: MovieDTO) = with(data) {
        Movie(
            movieId = movieId,
            title = title,
            posterPath = posterPath,
            backdropPath = backdropPath,
            summary = summary,
            rating = rating,
            popularity = popularity
        )
    }
}