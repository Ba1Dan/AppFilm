package ru.baiganov.appfilm.pojo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.baiganov.appfilm.converters.ConverterMovie

@Entity(tableName = "movies_list")
@TypeConverters(ConverterMovie::class)
@Parcelize
@Serializable
data class Movie(
        @PrimaryKey
        @SerialName("id")
        val id: Int,
        @SerialName("adult")
        val adult: Boolean,
        @SerialName("title")
        val title: String,
        @SerialName("genres")
        val genres: @RawValue List<Genre>,
        @SerialName("poster_path")
        var poster: String,
        @SerialName("backdrop_path")
        var backdrop: String,
        @SerialName("overview")
        val overview: String,
        @SerialName("vote_average")
        val ratings: Float,
        @SerialName("runtime")
        val runtime: Int,
        @SerialName("vote_count")
        val voteCount: Int
) : Parcelable