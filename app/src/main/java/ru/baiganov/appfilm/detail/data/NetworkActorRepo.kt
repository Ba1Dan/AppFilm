package ru.baiganov.appfilm.detail.data

import android.content.Context
import ru.baiganov.appfilm.database.AppDatabase
import ru.baiganov.appfilm.pojo.Actor
import ru.baiganov.appfilm.pojo.ActorsList

private const val ORIGINAL = "original"

class NetworkActorRepo(
        applicationContext: Context
) : ActorsRepository {

    private val database = AppDatabase.create(applicationContext)

    override suspend fun insertActors(actorsList: List<Actor>, id: Int) {
        database.actorsDao().insertActors(ActorsList(id, actorsList))
    }

    override suspend fun getActors(idMovie: Int): ActorsList {
        return database.actorsDao().getActors(idMovie)
    }
}