package com.mogo.moviescatalogue._2_moviedetail

import com.mogo.moviescatalogue.common.model.MovieItem

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movieItem: MovieItem?= null,
    val error: String = ""
)
