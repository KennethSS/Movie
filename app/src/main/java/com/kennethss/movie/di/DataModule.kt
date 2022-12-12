package com.kennethss.movie.di

import com.kennethss.movie.data.MovieRepositoryImpl
import com.kennethss.movie.domain.movie.movie.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsMovieRepository(
        repository: MovieRepositoryImpl
    ): MovieRepository
}