package com.mogo.data.repository

import com.mogo.data.mapper.MovieDetailMapper
import com.mogo.data.remote.NetworkService
import com.mogo.data.remote.handleNetworkCall
import com.mogo.domain.model.MovieInfo
import com.mogo.domain.repository.MovieDetailRepository
import com.mogo.domain.utils.Result
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val detailMapper: MovieDetailMapper
) : MovieDetailRepository {
    override suspend fun fetchMovieDetail(movieId: Int): Result<MovieInfo> {
        return handleNetworkCall {
            detailMapper.mapDetailDtoToDomainModel(
                networkService.getMovieDetail(
                    movieId
                )
            )
        }
    }
}