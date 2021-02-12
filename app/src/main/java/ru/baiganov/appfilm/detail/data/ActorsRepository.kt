package ru.baiganov.appfilm.detail.data

import ru.baiganov.appfilm.pojo.Actor
import ru.baiganov.appfilm.pojo.ActorsList

interface ActorsRepository {

    suspend fun getActors(idMovie: Int): ActorsList

    suspend fun insertActors(actorsList: ActorsList)
}