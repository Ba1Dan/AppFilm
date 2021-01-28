package ru.baiganov.appfilm.detail.data

import android.content.Context
import android.util.Log
import ru.baiganov.appfilm.api.ApiFactory
import ru.baiganov.appfilm.api.ApiService
import ru.baiganov.appfilm.database.AppDatabase
import ru.baiganov.appfilm.pojo.Actor
import ru.baiganov.appfilm.pojo.ActorsList

private const val ORIGINAL = "original"

class NetworkActorRepo(
        applicationContext: Context
) : ActorsRepository {

    private val database = AppDatabase.create(applicationContext)

    override suspend fun insertActorsInDatabase(actorsList: List<Actor>, id: Int) {
        database.actorsDao().insertActors(ActorsList(id, actorsList))
    }

    override suspend fun getActorsFromDatabase(): List<ActorsList> {
        return  database.actorsDao().getActors()
    }

    override suspend fun getActorsFromDatabase(idMovie: Int): ActorsList {
        return database.actorsDao().getActors(idMovie)
    }
}