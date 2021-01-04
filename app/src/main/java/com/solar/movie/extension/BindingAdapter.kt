package com.solar.movie.extension

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

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
@BindingAdapter("url")
fun loadImage(iv: AppCompatImageView, url: String?) {
    url?.let {
        Glide.with(iv.context)
            .load(url)
            .apply(RequestOptions.centerCropTransform())
            .into(iv)
    }
}

@BindingAdapter("circle")
fun loadCircleImage(iv: AppCompatImageView, url: String?) {
    url?.let {
        Glide.with(iv.context)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .into(iv)
    }
}