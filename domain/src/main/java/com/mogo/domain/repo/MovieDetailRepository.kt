package com.mogo.domain.repo

import com.mogo.domain.utils.Result
import com.mogo.domain.model.Movie

interface MovieDetailRepository {
    suspend fun fetchMovieDetail(movieId: Int): Result<Movie>
}