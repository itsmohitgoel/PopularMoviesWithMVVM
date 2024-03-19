package com.mogo.presentation.moviedetail

import com.mogo.presentation.common.model.MovieItem

data class MovieDetailViewState(
    val movie: MovieItem = MovieItem(),
    val loading: Boolean = false,
    val error: String = ""
)