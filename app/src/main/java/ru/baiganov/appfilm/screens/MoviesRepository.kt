package ru.baiganov.appfilm.screens

import ru.baiganov.appfilm.data.Movie

interface MoviesRepository {
    suspend fun getMovies(): List<Movie>
}