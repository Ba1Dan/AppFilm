package ru.baiganov.appfilm.list.data

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.baiganov.appfilm.api.ApiFactory.apiService
import ru.baiganov.appfilm.api.ApiService
import ru.baiganov.appfilm.database.AppDatabase

import ru.baiganov.appfilm.pojo.Movie
import ru.baiganov.appfilm.pojo.MoviePopular

private const val ORIGINAL = "original"

class NetworkMovieRepo(
    private val apiService: ApiService,
    private val applicationContext: Context
) : MoviesRepository {

    private val database = AppDatabase.create(applicationContext)

    override suspend fun getMovies(): List<Movie> {
        val movies = apiService.getPopularMovies().movies

        return movies.map {
            getMovie(it.id)
        }
    }

    override suspend fun getMovie(id: Int): Movie {
        val imageBaseUrl = apiService.getConfig().images.baseUrl
        val movie = apiService.getMovie(id)
        val fullBackdropUrl = imageBaseUrl + ORIGINAL + movie.backdrop
        val fullPoster = imageBaseUrl + ORIGINAL + movie.poster
        movie.backdrop = fullBackdropUrl
        movie.poster = fullPoster
        return movie
    }

    override suspend fun getMoviesFromDatabase() : List<Movie> = withContext(Dispatchers.IO) {
        return@withContext database.moviesDao().getData()
    }

    override suspend fun insertDataInDatabase(moviesList: List<Movie>) = withContext(Dispatchers.IO) {
        database.moviesDao().insertData(moviesList)
    }
}