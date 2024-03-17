package com.mogo.domain.usecase

import com.mogo.domain.model.MovieInfo
import com.mogo.domain.repository.MovieListRepository
import com.mogo.domain.utils.Result
import javax.inject.Inject

class MovieListUseCase @Inject constructor(private val movieListRepository: MovieListRepository){
    suspend fun execute() : Result<List<MovieInfo>> = movieListRepository.fetchMovies()
}
