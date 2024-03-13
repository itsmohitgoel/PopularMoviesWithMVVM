package com.mogo.data.repo

import com.mogo.common.utils.Resource
import com.mogo.data.mapper.MovieDetailMapper
import com.mogo.data.mapper.MovieListMapper
import com.mogo.data.remote.NetworkService
import com.mogo.data.remote.handleNetworkCall
import com.mogo.domain.model.Movie
import com.mogo.domain.repo.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val listMapper: MovieListMapper,
    private val detailMapper: MovieDetailMapper
) : MovieRepository {

    override suspend fun fetchMovies(): Resource<List<Movie>> {
        return handleNetworkCall { listMapper.map(networkService.getMovies().movies) }
    }

    override suspend fun fetchMovieDetail(movieId: Int): Resource<Movie> {
        return handleNetworkCall { detailMapper.map(networkService.getMovieDetail(movieId)) }
    }
}