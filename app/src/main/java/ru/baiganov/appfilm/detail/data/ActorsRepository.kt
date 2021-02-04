package ru.baiganov.appfilm.detail.data

import ru.baiganov.appfilm.pojo.Actor
import ru.baiganov.appfilm.pojo.ActorsList

interface ActorsRepository {

    suspend fun insertActors(actorsList: List<Actor>, id: Int)

    suspend fun getActors(idMovie: Int): ActorsList
}