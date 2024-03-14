package com.mogo.moviescatalogue.movielist

import com.mogo.moviescatalogue.common.model.MovieItem

data class MovieListState(
    val isLoading: Boolean = false,
    val movieList: List<MovieItem> = emptyList(),
    val error: String = ""
)
