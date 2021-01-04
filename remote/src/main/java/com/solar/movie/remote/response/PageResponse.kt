package com.solar.movie.remote.response

import com.google.gson.annotations.SerializedName

data class PageResponse<T>(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<T>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)