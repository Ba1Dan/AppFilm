package ru.baiganov.appfilm.pojo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Actor(
     @SerialName("id")
     val id: Int,
     @SerialName("name")
     val name: String,
     @SerialName("profile_path")
     var actorImageUrl: String?
)

@Serializable
data class ActorsList(
     @SerialName("id")
     val id: Int,
     @SerialName("cast")
     val actors: List<Actor>
)