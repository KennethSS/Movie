package com.solar.movie.presentation.movie.detail

import com.solar.movie.presentation.movie.detail.actor.ActorView
import com.solar.movie.presentation.movie.detail.backdrop.BackdropView

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
data class MovieDetailView(
    val id: Int,
    val title: String,
    val desc: String,
    val poster: String,
    val actors: List<ActorView>,
    val backdrops: List<BackdropView>
)