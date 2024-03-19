package com.mogo.domain.usecase

import com.mogo.domain.model.MovieInfo
import com.mogo.domain.repository.MovieDetailRepository
import com.mogo.domain.utils.Result
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(private val movieDetailRepository: MovieDetailRepository) {
    suspend fun execute(movieId: Int) : Result<MovieInfo> = movieDetailRepository.fetchMovieDetail(movieId)
}