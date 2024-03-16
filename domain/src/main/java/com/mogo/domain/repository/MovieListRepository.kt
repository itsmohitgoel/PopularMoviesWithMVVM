package com.mogo.domain.repository

import com.mogo.domain.utils.Result
import com.mogo.domain.model.MovieInfo

interface MovieListRepository {
    suspend fun fetchMovies() : Result<List<MovieInfo>>

}