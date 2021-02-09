package com.solar.movie.data

import com.nhaarman.mockitokotlin2.mock
import com.solar.movie.data.remote.MovieRemote
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DataTest {

    private lateinit var movieRemote: MovieRemote

    @Before
    fun setUp() {
        movieRemote = mock()
    }

    @Test
    fun `데이터 목 테스트`() = runBlocking {
        val result = movieRemote.getPopularMovie()
        println(result.toString())
    }
}