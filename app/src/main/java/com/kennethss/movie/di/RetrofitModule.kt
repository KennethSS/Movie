package com.kennethss.movie.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kennethss.movie.BuildConfig
import com.kennethss.movie.remote.interceptor.KeyInterceptor
import com.kennethss.movie.remote.service.MovieDbService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient
        .Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        )
        .addInterceptor(KeyInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideMovieService(client: OkHttpClient): MovieDbService {
        val contentType = "application/json".toMediaType()
        val factory = Json {
            isLenient = true
            ignoreUnknownKeys = true
            coerceInputValues = true
        }.asConverterFactory(contentType)

        return Retrofit.Builder()
            .addConverterFactory(factory)
            .client(client)
            .baseUrl("https://api.themoviedb.org/")
            .build()
            .create(MovieDbService::class.java)
    }
}