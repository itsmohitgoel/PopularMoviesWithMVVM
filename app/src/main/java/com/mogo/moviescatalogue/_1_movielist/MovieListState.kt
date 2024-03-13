package com.mogo.moviescatalogue._1_movielist

import com.mogo.moviescatalogue.common.model.MovieItem

data class MovieListState(
    val isLoading: Boolean = false,
    val movieList: List<MovieItem> = emptyList(),
    val error: String = ""
)
