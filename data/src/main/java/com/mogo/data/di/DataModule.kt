package com.mogo.data.di

import com.mogo.data.repo.MovieDetailRepositoryImpl
import com.mogo.data.repo.MovieListRepositoryImpl
import com.mogo.domain.repo.MovieDetailRepository
import com.mogo.domain.repo.MovieListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindMovieListRepository(repositoryImpl: MovieListRepositoryImpl): MovieListRepository

    @Binds
    abstract fun bindMovieDetailRepository(
        movieDetailRepositoryImpl: MovieDetailRepositoryImpl
    ): MovieDetailRepository
}
