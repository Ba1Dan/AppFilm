package ru.baiganov.appfilm.list.data


import androidx.lifecycle.LiveData
import ru.baiganov.appfilm.pojo.Actor
import ru.baiganov.appfilm.pojo.ActorsList
import ru.baiganov.appfilm.pojo.Movie
import ru.baiganov.appfilm.pojo.MoviePopular

interface MoviesRepository {

    suspend fun getMovies(): List<Movie>

    suspend fun getMovie(id: Int): Movie

    suspend fun getMoviesFromDatabase(): List<Movie>

    suspend fun insertDataInDatabase(moviesList: List<Movie>)

    suspend fun insertActorsInDatabase(actorsList: ActorsList)

    suspend fun getActorsFromNetwork(id: Int): ActorsList

    fun checkNetwork(): Boolean
}