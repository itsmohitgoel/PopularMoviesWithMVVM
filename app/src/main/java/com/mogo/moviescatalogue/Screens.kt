package com.mogo.moviescatalogue

sealed class Screens(val route: String) {
    object MovieListScreenIdentifier : Screens("movie_list_screen")
    object MovieDetailScreenIdentifier : Screens("movie_detail_screen")
}