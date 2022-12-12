package com.kennethss.movie.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class KeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", "1758a2b5e3805a44db15e138d4845e60")
            .addQueryParameter("language", "ko")
            .build()


        val requestBuilder = original.newBuilder()
            .url(url)

        val request = requestBuilder.build()

        return chain.proceed(request)
    }
}