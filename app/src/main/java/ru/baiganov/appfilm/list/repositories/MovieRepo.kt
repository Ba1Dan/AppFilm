package ru.baiganov.appfilm.list.repositories

import androidx.lifecycle.LiveData
import retrofit2.HttpException
import ru.baiganov.appfilm.api.ApiService
import ru.baiganov.appfilm.database.MoviesDao
import ru.baiganov.appfilm.pojo.Movie

class MovieRepo(
        private val database: MoviesDao,
        private val apiService: ApiService
) : MoviesRepository {

    override suspend fun getMovies(): LiveData<List<Movie>> {
        updateData()
        return database.getMovies()
    }

    private suspend fun updateData() {
        try {
            val moviesJson = apiService.getPopularMovies().movies
            val movies = moviesJson.map {
                getMovie(it.id)
            }
            database.insertMovies(movies)
        } catch (e: Exception) {
            throw RuntimeException()
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

    override suspend fun delete() {
        database.deleteAllMovies()
    }

    companion object {
        private const val ORIGINAL = "original"
    }
}