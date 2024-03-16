package com.mogo.moviescatalogue.movielist

import com.mogo.moviescatalogue.common.model.MovieItem

data class MovieListState(
    val movies: List<MovieItem> = emptyList(),
    val loading: Boolean = false,
    val error: String = ""
)