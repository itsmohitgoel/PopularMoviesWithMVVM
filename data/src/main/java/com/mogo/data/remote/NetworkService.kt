package com.mogo.data.remote

import com.mogo.data.model.MovieDTO
import com.mogo.data.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {
    @GET("/3/movie/popular?api_key=b3575a4d65df55868c790b3c3aa1ae71&language=en-US&page=1&language=en-US&page=1")
    suspend fun getMovies(): MoviesResponse

    @GET("/3/movie/{movieID}?api_key=b3575a4d65df55868c790b3c3aa1ae71&language=en-US&page=1&language=en-US")
    suspend fun getMovieDetail(@Path("movieID") movieID: Int): MovieDTO
}