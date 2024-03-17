package com.mogo.presentation.moviedetail

sealed interface MovieDetailAction{
    data class LoadMovieDetail(val movieId : Int) : MovieDetailAction
    // TODO: Add Like /dislike button action
}