package com.solar.movie.presentation.movie.detail.actor

import com.solar.movie.R
import com.solar.recyclerview.adapter.holder.ItemType

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
data class ActorView(
    val name: String,
    val profile: String,
    val character: String,
    override val layoutRes: Int = R.layout.item_actor_circle
): ItemType