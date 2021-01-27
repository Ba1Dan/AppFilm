package ru.baiganov.appfilm.pojo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesData(
    val page: Int,
    @SerialName("results")
    val movies: List<MoviePopular>,
    val total_pages: Int,
    val total_results: Int
)