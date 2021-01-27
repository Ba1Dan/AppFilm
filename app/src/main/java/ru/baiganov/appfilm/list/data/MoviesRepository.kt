package ru.baiganov.appfilm.list.data


import ru.baiganov.appfilm.pojo.Movie
import ru.baiganov.appfilm.pojo.MoviePopular

interface MoviesRepository {
    suspend fun getMovies(): List<Movie>
    suspend fun getMovie(id: Int): Movie
}