package ru.baiganov.appfilm.pojo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
        @SerialName("id")
        val id: Int,
        @SerialName("poster_path")
        val posterPath: String,
        @SerialName("adult")
        val adult: Boolean,
        @SerialName("overview")
        val overview: String,
        @SerialName("release_date")
        val releaseDate: String,
        @SerialName("genre_ids")
        val genres: List<Int>,
        @SerialName("original_title")
        val originalTitle: String,
        @SerialName("original_language")
        val originalLanguage: String,
        @SerialName("title")
        val title: String,
        @SerialName("backdrop_path")
        val backdropPath: String,
        @SerialName("popularity")
        val popularity: Double,
        @SerialName("vote_count")
        val voteCount: Int,
        @SerialName("video")
        val video: Boolean,
        @SerialName("vote_average")
        val voteAverage: Double
)