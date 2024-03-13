package com.mogo.domain.model

data class Movie (
    val movieId: Int,
    val title : String,
    val posterPath : String,
    val backdropPath : String,
    val summary : String,
    val rating : Double,
    val popularity : Double
)