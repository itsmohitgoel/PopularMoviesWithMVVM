package com.mogo.moviescatalogue.moviedetail

import com.mogo.presentation.common.model.MovieItem

data class MovieDetailStateOld(
    val isLoading: Boolean = false,
    val movieItem: MovieItem?= null,
    val error: String = ""
)
