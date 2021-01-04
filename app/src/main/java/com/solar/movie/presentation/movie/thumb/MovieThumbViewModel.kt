package com.solar.movie.presentation.movie.thumb

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.solar.movie.presentation.movie.detail.MovieDetailActivity

class MovieThumbViewModel : ViewModel() {
    fun showMovieDetail(context: Context, movieId: Int) {
        MovieDetailActivity.start(context, movieId)
    }
}