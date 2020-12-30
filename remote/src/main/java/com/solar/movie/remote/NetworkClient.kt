package com.solar.movie.remote

import com.solar.movie.remote.okhttp.KeyInterceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *  Created by Kenneth on 12/30/20
 */
object NetworkClient {
    private const val CONNECTION_TIMEOUT = 10L
    private const val WRITE_TIMEOUT = 30L
    private const val READ_TIMEOUT = 30L

    inline fun <reified T>provideService(isDebug: Boolean): T  {
        val retrofit = getRetrofit(
            buildOkHttpInterceptor(isDebug),
            MOVIE_HOST
        )
        return retrofit.create(T::class.java)
    }

    fun buildOkHttpInterceptor(isDebug: Boolean): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(KeyInterceptor())

        if (isDebug) {
            httpClientBuilder
                .addNetworkInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                )
        }

        httpClientBuilder.addNetworkInterceptor { chain ->
            val request = chain.request()
            val builder: Request.Builder = request.newBuilder()
            chain.proceed(builder.build())
        }

        return httpClientBuilder.build()
    }

    fun getRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }
}