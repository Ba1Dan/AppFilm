package ru.baiganov.appfilm.list.repositories

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.baiganov.appfilm.api.ApiService
import ru.baiganov.appfilm.database.MoviesDao
import ru.baiganov.appfilm.pojo.Movie

class MovieRepo(
        private val database: MoviesDao,
        private val apiService: ApiService
) : MoviesRepository {

    override suspend fun getMovies(): LiveData<List<Movie>> {
        return database.getMovies()
    }

    override suspend fun updateData() {
        val moviesJson = apiService.getPopularMovies().movies
        val movies = moviesJson.map {
            getMovie(it.id)
        }
        database.insertMovies(movies)
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

    override suspend fun delete() {
        database.deleteAllMovies()
    }

    companion object {
        private const val ORIGINAL = "original"
    }
}