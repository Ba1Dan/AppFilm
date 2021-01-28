package ru.baiganov.appfilm.detail.data

import ru.baiganov.appfilm.pojo.Actor
import ru.baiganov.appfilm.pojo.ActorsList

interface ActorsRepository {

    suspend fun insertActorsInDatabase(actorsList: List<Actor>, id: Int)

    suspend fun getActorsFromDatabase(): List<ActorsList>

    suspend fun getActorsFromDatabase(idMovie: Int): ActorsList
}