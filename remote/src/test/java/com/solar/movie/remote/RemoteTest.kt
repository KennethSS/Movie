package com.solar.movie.remote

import com.solar.movie.remote.service.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.setMain
import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class RemoteTest {
    private lateinit var movieService: MovieService

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setup() {
        movieService = NetworkClient.provideService(false)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `영화 API 정보 가져오기`() {
        movieService.getMovieDetailById()
            .test()
            .assertComplete()
    }

    @Test
    fun `영화 API 정보 가져오기(Coroutine)`() = runBlocking {
        movieService.getMovieDetailByIdFromCoroutine(550)
    }

    @Test
    fun `영화 API 정보 데이터 검증`() {
        movieService.getMovieDetailById()
            .test()
            .assertSubscribed()
            .assertValue { value ->
                val isExist = value.genres?.isNotEmpty() ?: false

                if (isExist) {
                    value.genres!![0].id == 18
                } else {
                    false
                }
            }
            .assertComplete()
            .assertNoErrors()
    }
}