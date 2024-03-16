package com.mogo.data.di

import com.mogo.data.repository.MovieDetailRepositoryImpl
import com.mogo.data.repository.MovieListRepositoryImpl
import com.mogo.domain.repository.MovieDetailRepository
import com.mogo.domain.repository.MovieListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMovieListRepository(repositoryImpl: MovieListRepositoryImpl): MovieListRepository

    @Binds
    @Singleton
    abstract fun bindMovieDetailRepository(movieDetailRepositoryImpl: MovieDetailRepositoryImpl): MovieDetailRepository
}
