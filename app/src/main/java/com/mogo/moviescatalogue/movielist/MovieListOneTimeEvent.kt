package com.mogo.moviescatalogue.movielist

sealed interface MovieListOneTimeEvent {
    data class NavigateToDetailScreen(val movieId: Int) : MovieListOneTimeEvent
}