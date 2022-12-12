package com.kennethss.movie.remote.response.detail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Credits(
    @SerialName("cast")
    val cast: List<Cast> = listOf(),
    @SerialName("crew")
    val crew: List<Crew> = listOf()
)