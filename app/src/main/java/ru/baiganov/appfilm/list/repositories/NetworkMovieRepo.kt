package ru.baiganov.appfilm.list.repositories

import ru.baiganov.appfilm.api.ApiService
import ru.baiganov.appfilm.pojo.Movie

private const val ORIGINAL = "original"

class NetworkMovieRepo(private val apiService: ApiService) : MoviesRepository {

    override suspend fun insertMovies(moviesList: List<Movie>) {
        TODO("Not yet implemented")
    }

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

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }
}