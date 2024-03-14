package com.mogo.data.repo

import com.mogo.domain.utils.Result
import com.mogo.data.mapper.MovieDetailMapper
import com.mogo.data.remote.NetworkService
import com.mogo.data.remote.handleNetworkCall
import com.mogo.domain.model.Movie
import com.mogo.domain.repo.MovieDetailRepository
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val detailMapper: MovieDetailMapper
) : MovieDetailRepository{
    override suspend fun fetchMovieDetail(movieId: Int): Result<Movie> {
        return handleNetworkCall { detailMapper.mapDetailDTOToDomainModel(networkService.getMovieDetail(movieId)) }
    }
}