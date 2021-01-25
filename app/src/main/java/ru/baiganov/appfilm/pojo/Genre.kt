package ru.baiganov.appfilm.pojo

import kotlinx.serialization.Serializable

@Serializable
data class Genre (
        val id: Int,
        val name: String
)