package com.mogo.data.mapper

import com.mogo.data.Constants.DEFAULT_TAGLINE
import com.mogo.data.model.MovieDTO
import com.mogo.domain.model.Movie
import javax.inject.Inject

class MovieListMapper @Inject constructor()  {
     fun mapListDTOToDomainModel(data: List<MovieDTO>) = data.map { movieDTO ->
        with(movieDTO) {
            Movie(
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

}