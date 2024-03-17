package com.mogo.presentation.moviedetail

import com.mogo.presentation.common.model.MovieItem

data class MovieDetailState(
    val movie: MovieItem = MovieItem(),
    val loading: Boolean = false,
    val error: String = ""
)