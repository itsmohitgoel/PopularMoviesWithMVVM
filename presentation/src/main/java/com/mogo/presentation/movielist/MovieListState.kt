package com.mogo.presentation.movielist

import com.mogo.presentation.common.model.MovieItem

data class MovieListState(
    val movies: List<MovieItem> = emptyList(),
    val loading: Boolean = false,
    val error: String = ""
)