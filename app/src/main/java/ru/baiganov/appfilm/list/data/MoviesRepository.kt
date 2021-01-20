package ru.baiganov.appfilm.list.data

import ru.baiganov.appfilm.data.Movie

interface MoviesRepository {
    suspend fun getMovies(): List<Movie>
}