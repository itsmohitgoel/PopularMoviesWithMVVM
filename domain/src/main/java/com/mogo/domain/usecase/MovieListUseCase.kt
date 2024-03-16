package com.mogo.domain.usecase

import com.mogo.domain.repository.MovieListRepository
import javax.inject.Inject

class MovieListUseCase @Inject constructor(private val movieListRepository: MovieListRepository){
    suspend fun execute() = movieListRepository.fetchMovies()
}
