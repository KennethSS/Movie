package com.solar.movie.di.module

import com.solar.movie.data.entity.mapper.MovieEntityMapper
import com.solar.movie.data.impl.MovieRepositoryImpl
import com.solar.movie.data.local.MovieLocal
import com.solar.movie.data.remote.MovieRemote
import com.solar.movie.domain.repository.repository.MovieRepository
import com.solar.movie.domain.repository.usecase.FavoriteUseCase
import com.solar.movie.domain.repository.usecase.MovieUseCase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Copyright 2020 Kenneth
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **/
@Module
@InstallIn(ApplicationComponent::class)
object DomainModule {

    @Provides
    fun provideMovieEntityMapper(): MovieEntityMapper = MovieEntityMapper()

    @Provides
    @Reusable
    fun provideMovieRepository(remote: MovieRemote,
                               local: MovieLocal,
                               mapper: MovieEntityMapper): MovieRepository {

        return MovieRepositoryImpl(remote, local, mapper)
    }

    @Provides
    fun provideMovieUseCase(repository: MovieRepository): MovieUseCase {
        return MovieUseCase(repository)
    }

    @Provides
    fun provideFavoriteUseCase(repository: MovieRepository): FavoriteUseCase {
        return FavoriteUseCase((repository))
    }
}
