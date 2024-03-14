package com.mogo.data.repo

import com.mogo.domain.utils.Result
import com.mogo.data.mapper.MovieListMapper
import com.mogo.data.remote.NetworkService
import com.mogo.data.remote.handleNetworkCall
import com.mogo.domain.model.Movie
import com.mogo.domain.repo.MovieListRepository
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val listMapper: MovieListMapper
) : MovieListRepository {

    override suspend fun fetchMovies(): Result<List<Movie>> {
        return handleNetworkCall { listMapper.mapListDTOToDomainModel(networkService.getMovies().movies) }
    }
}