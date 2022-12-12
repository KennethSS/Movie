package com.kennethss.movie.di

import com.kennethss.movie.data.MovieDbRemoteDataSource
import com.kennethss.movie.remote.moviedb.MovieDbRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteModule {

    @Singleton
    @Binds
    abstract fun bindsMovieDbRemoteDataSource(source: MovieDbRemoteDataSourceImpl): MovieDbRemoteDataSource
}