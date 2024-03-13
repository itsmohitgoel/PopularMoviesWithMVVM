package com.mogo.data.di

import com.mogo.data.repo.MovieRepositoryImpl
import com.mogo.domain.repo.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindRepository(repositoryImpl: MovieRepositoryImpl): MovieRepository
}
