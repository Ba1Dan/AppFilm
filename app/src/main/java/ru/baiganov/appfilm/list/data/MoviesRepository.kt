package ru.baiganov.appfilm.list.data


import androidx.lifecycle.LiveData
import ru.baiganov.appfilm.pojo.Movie
import ru.baiganov.appfilm.pojo.MoviePopular

interface MoviesRepository {

    suspend fun getMovies(): List<Movie>

    suspend fun getMovie(id: Int): Movie

    suspend fun getMoviesFromDatabase(): List<Movie>

    suspend fun insertDataInDatabase(moviesList: List<Movie>)

}