package com.mogo.domain.usecase

import com.mogo.domain.repo.MovieRepository
import javax.inject.Inject

class MovieListUseCase @Inject constructor(private val movieRepository: MovieRepository){
    suspend fun execute() = movieRepository.fetchMovies()
}
