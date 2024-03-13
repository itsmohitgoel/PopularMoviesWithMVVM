package com.mogo.moviescatalogue.common.model

data class MovieItem(
    val movieId: Int = 0,
    val title : String = "",
    val imageUrl : String = "",
    val backdropPath : String = "",
    val summary : String = "",
    val rating : Double = 0.0,
    val popularity : Double = 0.0
)
