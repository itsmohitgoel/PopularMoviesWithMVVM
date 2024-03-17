package com.mogo.presentation.movielist

sealed interface MovieListOneTimeEvent {
    data class NavigateToDetailScreen(val movieId: Int) : MovieListOneTimeEvent
}