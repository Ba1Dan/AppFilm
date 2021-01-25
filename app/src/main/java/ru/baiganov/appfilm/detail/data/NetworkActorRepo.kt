package ru.baiganov.appfilm.detail.data

import android.util.Log
import ru.baiganov.appfilm.api.ApiFactory
import ru.baiganov.appfilm.pojo.Actor

private const val ORIGINAL = "original"

class NetworkActorRepo : ActorsRepository {

    override suspend fun getActors(id: Int): List<Actor> {
        val actors = ApiFactory.apiService.getActors(id).actors
        val imageBaseUrl = ApiFactory.apiService.getConfig().images.baseUrl + ORIGINAL
        actors.map {
            val fullUrl = imageBaseUrl + it.actorImageUrl
            it.actorImageUrl = fullUrl
        }
        return actors
    }
}