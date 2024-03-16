package com.mogo.domain.repository

import com.mogo.domain.utils.Result
import com.mogo.domain.model.MovieInfo

interface MovieDetailRepository {
    suspend fun fetchMovieDetail(movieId: Int): Result<MovieInfo>
}