package com.mogo.domain.usecase

import com.mogo.domain.repo.MovieRepository
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(private val movieRepository: MovieRepository){
    suspend fun execute(movieId : Int) = movieRepository.fetchMovieDetail(movieId)
}