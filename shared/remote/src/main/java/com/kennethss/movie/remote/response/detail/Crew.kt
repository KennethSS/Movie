package com.kennethss.movie.remote.response.detail


import com.kennethss.movie.data.movie.DirectorData
import com.kennethss.movie.remote.response.popular.IMAGE_BASE_HOST
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Crew(
    @SerialName("adult")
    val adult: Boolean = false,
    @SerialName("credit_id")
    val creditId: String = "",
    @SerialName("department")
    val department: String = "",
    @SerialName("gender")
    val gender: Int = 0,
    @SerialName("id")
    val id: Int = 0,
    @SerialName("job")
    val job: String = "",
    @SerialName("known_for_department")
    val knownForDepartment: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("original_name")
    val originalName: String = "",
    @SerialName("popularity")
    val popularity: Double = 0.0,
    @SerialName("profile_path")
    val profilePath: String = ""
)

fun Crew.toDirectorData() = com.kennethss.movie.data.movie.DirectorData(
    id = id,
    name = name,
    profileUrl = IMAGE_BASE_HOST + profilePath
)