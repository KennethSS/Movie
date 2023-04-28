package com.kennethss.movie.di

import com.kennethss.movie.data.movie.MovieRepositoryImpl
import com.kennethss.movie.data.actor.ActorRepositoryImpl
import com.kennethss.movie.domain.actor.ActorRepository
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
        repository: com.kennethss.movie.data.movie.MovieRepositoryImpl
    ): MovieRepository

    @Binds
    fun bindsActorRepository(
        repository: ActorRepositoryImpl
    ): ActorRepository

}