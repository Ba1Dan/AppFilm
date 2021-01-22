package ru.baiganov.appfilm.list.data


import ru.baiganov.appfilm.pojo.Movie

interface MoviesRepository {
    suspend fun getMovies(): List<Movie>
}