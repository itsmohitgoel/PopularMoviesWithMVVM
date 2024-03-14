package com.mogo.domain.repo

import com.mogo.domain.utils.Result
import com.mogo.domain.model.Movie

interface MovieListRepository {
    suspend fun fetchMovies() : Result<List<Movie>>

}