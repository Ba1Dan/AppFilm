package ru.baiganov.appfilm.detail.repositories

import ru.baiganov.appfilm.api.ApiService
import ru.baiganov.appfilm.pojo.ActorsList

class NetworkActorRepo(private val apiService: ApiService) : ActorsRepository{

    override suspend fun getActors(idMovie: Int): ActorsList {
        val actors = apiService.getActors(idMovie).actors
        val imageBaseUrl = apiService.getConfig().images.baseUrl + ORIGINAL
        actors.map {
            val fullUrl = imageBaseUrl + it.actorImageUrl
            it.actorImageUrl = fullUrl
        }
        return ActorsList(idMovie, actors)
    }

    override suspend fun insertActors(actorsList: ActorsList) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val ORIGINAL = "original"
    }
}