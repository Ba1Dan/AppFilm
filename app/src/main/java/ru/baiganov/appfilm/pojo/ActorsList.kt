package ru.baiganov.appfilm.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.baiganov.appfilm.converters.ConverterActor

@Entity(tableName = "actors_list")
@TypeConverters(ConverterActor::class)
@Serializable
data class ActorsList(
        @PrimaryKey
        @SerialName("id")
        val id: Int,
        @SerialName("cast")
        val actors: List<Actor>
)