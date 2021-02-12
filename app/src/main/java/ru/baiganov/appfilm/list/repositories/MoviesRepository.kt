package ru.baiganov.appfilm.list.repositories

import ru.baiganov.appfilm.pojo.Movie

interface MoviesRepository {

    suspend fun getMovies(): List<Movie>

    suspend fun getMovie(id: Int): Movie

    suspend fun insertMovies(moviesList: List<Movie>)

    suspend fun deleteAll()
}