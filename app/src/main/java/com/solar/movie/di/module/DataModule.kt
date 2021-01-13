package com.solar.movie.di.module


import android.content.Context
import com.solar.movie.BuildConfig
import com.solar.movie.data.local.MovieLocal
import com.solar.movie.data.remote.MovieRemote
import com.solar.movie.db.AppDatabase
import com.solar.movie.local.db.impl.MovieLocalImpl
import com.solar.movie.local.db.mapper.MovieLocalMapper
import com.solar.movie.remote.NetworkClient
import com.solar.movie.remote.impl.MovieRemoteImpl
import com.solar.movie.remote.response.mapper.MovieDetailMapper
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

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
object DataModule {

    @Provides
    fun provideMovieDetailMapper(): MovieDetailMapper = MovieDetailMapper()

    @Provides
    fun provideMovieLocalMapper(): MovieLocalMapper = MovieLocalMapper()

    @Provides
    fun provideMovieRemote(mapper: MovieDetailMapper): MovieRemote {
        return MovieRemoteImpl(NetworkClient.provideService(BuildConfig.DEBUG), mapper)
    }

    @Provides
    fun provideMovieLocal(@ApplicationContext appContext: Context,
                          mapper: MovieLocalMapper): MovieLocal {
        return MovieLocalImpl(AppDatabase.getInstance(appContext).movieDao(), mapper)
    }
}
