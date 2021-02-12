package ru.baiganov.appfilm.detail.repositories

import ru.baiganov.appfilm.database.ActorsDao
import ru.baiganov.appfilm.pojo.ActorsList

class DatabaseActorRepo(private val actorsDao: ActorsDao) : ActorsRepository {

    override suspend fun getActors(idMovie: Int): ActorsList {
        return actorsDao.getActors(idMovie)
    }

    override suspend fun insertActors(actorsList: ActorsList) {
        actorsDao.insertActors(actorsList)
    }
}