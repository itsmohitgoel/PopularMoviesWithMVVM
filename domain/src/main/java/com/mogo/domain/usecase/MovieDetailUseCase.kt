package com.mogo.domain.usecase

import com.mogo.domain.repository.MovieDetailRepository
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(private val movieDetailRepository: MovieDetailRepository) {
    suspend fun execute(movieId: Int) = movieDetailRepository.fetchMovieDetail(movieId)
}