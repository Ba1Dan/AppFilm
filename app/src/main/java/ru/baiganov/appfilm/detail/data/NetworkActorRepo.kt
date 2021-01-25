package ru.baiganov.appfilm.detail.data

import android.util.Log
import ru.baiganov.appfilm.api.ApiFactory
import ru.baiganov.appfilm.pojo.Actor

class NetworkActorRepo : ActorsRepository {

    override suspend fun getActors(id: Int): List<Actor> {
        val actors = ApiFactory.apiService.getActors(id).actors
        Log.i("IN NETWORKREPO", actors.toString())
        val imageBaseUrl = ApiFactory.apiService.getConfig().images.baseUrl + "original"
        actors.map {
            val fullUrl = imageBaseUrl + it.actorImageUrl
            it.actorImageUrl = fullUrl
        }
        Log.i("IN NETWORKREPO", actors.toString())
        return actors
    }
}