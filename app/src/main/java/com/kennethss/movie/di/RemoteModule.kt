package com.kennethss.movie.di

import com.kennethss.movie.data.movie.MovieDbRemoteDataSource
import com.kennethss.movie.data.actor.ActorRemoteDataSource
import com.kennethss.movie.remote.actor.ActorRemoteDataSourceImpl
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
    abstract fun bindsMovieDbRemoteDataSource(source: MovieDbRemoteDataSourceImpl): com.kennethss.movie.data.movie.MovieDbRemoteDataSource

    @Binds
    abstract fun bindsActorRemoteDataSource(source: ActorRemoteDataSourceImpl): ActorRemoteDataSource
}