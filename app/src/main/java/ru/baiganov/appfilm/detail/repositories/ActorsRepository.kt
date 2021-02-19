package ru.baiganov.appfilm.detail.repositories

import ru.baiganov.appfilm.pojo.ActorsList

interface ActorsRepository {

    suspend fun getActors(idMovie: Int): ActorsList

    suspend fun insertActors(actorsList: ActorsList)
}