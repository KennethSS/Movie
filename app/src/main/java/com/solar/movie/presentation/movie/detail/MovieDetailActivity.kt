package com.solar.movie.presentation.movie.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.solar.library.binding.activity.BindingActivity
import com.solar.movie.NetworkState
import com.solar.movie.R
import com.solar.movie.databinding.ActivityMovieDetailBinding
import com.solar.movie.extension.observe
import dagger.hilt.android.AndroidEntryPoint

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
@AndroidEntryPoint
class MovieDetailActivity : BindingActivity() {

    private val binding: ActivityMovieDetailBinding by binding(R.layout.activity_movie_detail)

    private val movieDetailViewModel: MovieDetailViewModel by viewModels()

    private val movieId: Int by lazy { intent.getIntExtra("id", 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        observe(movieDetailViewModel.movieDetailLiveData) {
            when(it) {
                is NetworkState.Success -> {
                    binding.movie = it.item
                    Log.d("MovieDetailActivity", it.item.toString())
                }
                else -> {}
            }
        }

        movieDetailViewModel.getMovieDetail(movieId)
    }


    companion object {
        fun start(context: Context, id: Int) {
            context.startActivity(Intent(context, MovieDetailActivity::class.java).apply {
                putExtra("id", id)
            })
        }
    }
}