package com.mogo.presentation.movielist

sealed interface MovieListAction{
    data object LoadMovieList : MovieListAction
    data class MovieListItemClick(val movieId: Int) : MovieListAction
}