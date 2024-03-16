package com.mogo.moviescatalogue.movielist

sealed interface MovieListAction{
    data object LoadMovieList : MovieListAction
    data class ClickMovieListItem(val movieId: Int) : MovieListAction
}