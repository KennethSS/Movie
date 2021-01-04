package com.solar.movie.presentation.movie.list

import com.solar.movie.R
import com.solar.movie.presentation.home.HomeItem
import com.solar.movie.presentation.movie.thumb.MovieThumbView

data class MovieListView(
    val title: String,
    val list: List<MovieThumbView>,
    override val layoutRes: Int = R.layout.item_movie_list
) : HomeItem()