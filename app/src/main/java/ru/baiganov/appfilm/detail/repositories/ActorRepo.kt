package ru.baiganov.appfilm.detail.repositories

import ru.baiganov.appfilm.pojo.ActorsList

class ActorRepo(
        private val networkActorRepo: NetworkActorRepo,
        private val databaseActorRepo: DatabaseActorRepo
) : ActorsRepository{

    override suspend fun getActors(idMovie: Int): ActorsList {
        return try {
            networkActorRepo.getActors(idMovie)
        } catch (e: Exception) {
            e.printStackTrace()
            databaseActorRepo.getActors(idMovie)
        }
    }

    override suspend fun insertActors(actorsList: ActorsList) {
        databaseActorRepo.insertActors(actorsList)
    }
}