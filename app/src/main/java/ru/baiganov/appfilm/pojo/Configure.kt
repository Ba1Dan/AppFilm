package ru.baiganov.appfilm.pojo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class Configure(
        val images: Images
)

@Serializable
data class Images(
        @SerialName("base_url")
        val baseUrl: String
)