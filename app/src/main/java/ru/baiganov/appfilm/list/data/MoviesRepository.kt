package ru.baiganov.appfilm.list.data

import ru.baiganov.appfilm.pojo.ActorsList
import ru.baiganov.appfilm.pojo.Movie

interface MoviesRepository {

    suspend fun getMovies(): List<Movie>

    suspend fun getMovie(id: Int): Movie

    suspend fun getMoviesFromDatabase(): List<Movie>

    suspend fun insertMovies(moviesList: List<Movie>)

    //suspend fun insertActors(actorsList: ActorsList)

    //suspend fun getActors(id: Int): ActorsList

    suspend fun deleteAll()
}