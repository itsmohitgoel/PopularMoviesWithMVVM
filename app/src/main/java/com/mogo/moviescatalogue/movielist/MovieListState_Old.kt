package com.mogo.moviescatalogue.movielist

import com.mogo.presentation.common.model.MovieItem

data class MovieListState_Old(
    val isLoading: Boolean = false,
    val movies: List<MovieItem> = emptyList(),
    val error: String = ""
)
