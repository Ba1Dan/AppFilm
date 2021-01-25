package ru.baiganov.appfilm.pojo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviePopular (
        @SerialName("id")
        val id: Int
)