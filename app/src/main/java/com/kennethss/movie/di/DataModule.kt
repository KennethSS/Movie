package com.kennethss.movie.di

import com.kennethss.movie.data.MovieRepositoryImpl
import com.kennethss.movie.data.actor.ActorRepositoryImpl
import com.kennethss.movie.domain.movie.actor.ActorRepository
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

    @Binds
    fun bindsActorRepository(
        repository: ActorRepositoryImpl
    ): ActorRepository

}