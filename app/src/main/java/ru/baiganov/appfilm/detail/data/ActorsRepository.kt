package ru.baiganov.appfilm.detail.data

import ru.baiganov.appfilm.pojo.Actor

interface ActorsRepository {
    suspend fun getActors(id: Int): List<Actor>
}