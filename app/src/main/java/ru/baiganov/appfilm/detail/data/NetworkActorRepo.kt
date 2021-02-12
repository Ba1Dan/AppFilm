package ru.baiganov.appfilm.detail.data

import ru.baiganov.appfilm.api.ApiService
import ru.baiganov.appfilm.database.ActorsDao
import ru.baiganov.appfilm.pojo.ActorsList

private const val ORIGINAL = "original"

class NetworkActorRepo(
        private val apiService: ApiService,
        private val actorsDao: ActorsDao,
) : ActorsRepository {

    /*override suspend fun getActors(idMovie: Int): ActorsList {
        return actorsDao.getActors(idMovie)
    }*/

    override suspend fun getActors(id: Int): ActorsList {
        val actors = apiService.getActors(id).actors
        val imageBaseUrl = apiService.getConfig().images.baseUrl + ORIGINAL
        actors.map {
            val fullUrl = imageBaseUrl + it.actorImageUrl
            it.actorImageUrl = fullUrl
        }
        return ActorsList(id, actors)
    }

    override suspend fun insertActors(actorsList: ActorsList) {
        actorsDao.insertActors(actorsList = actorsList)
    }
}