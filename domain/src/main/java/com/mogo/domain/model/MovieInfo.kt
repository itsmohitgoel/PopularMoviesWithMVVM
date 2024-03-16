package com.mogo.domain.model

data class MovieInfo (
    val movieId: Int,
    val title : String,
    val posterPath : String,
    val backdropPath : String,
    val summary : String,
    val rating : Double,
    val popularity : Double,
    val releaseDate : String,
    val tagline : String
)