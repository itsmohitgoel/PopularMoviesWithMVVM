package com.mogo.data.repository

import com.mogo.data.mapper.MovieListMapper
import com.mogo.data.remote.NetworkService
import com.mogo.data.remote.handleNetworkCall
import com.mogo.domain.model.MovieInfo
import com.mogo.domain.repository.MovieListRepository
import com.mogo.domain.utils.Result
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val listMapper: MovieListMapper
) : MovieListRepository {
    override suspend fun fetchMovies(): Result<List<MovieInfo>> {
        return handleNetworkCall {
            listMapper.mapMovieDtoListToMovieInfoList(
                networkService.getMovies().moviesList
            )
        }
    }
}