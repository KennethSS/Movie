package com.solar.movie.presentation.movie.thumb

import com.solar.movie.R
import com.solar.recyclerview.adapter.holder.ItemType

data class MovieThumbView(
    val id: Int,
    val poster: String,
    val name: String,
    val releaseDate: String,
    override val layoutRes: Int = R.layout.item_movie_thumb
) : ItemType