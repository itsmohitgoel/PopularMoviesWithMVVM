package com.mogo.domain.repo

import com.mogo.common.utils.Resource
import com.mogo.domain.model.Movie

interface MovieRepository {
    suspend fun fetchMovies() : Resource<List<Movie>>
    suspend fun fetchMovieDetail(movieId : Int) : Resource<Movie>
}