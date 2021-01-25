package ru.baiganov.appfilm.list.data

import ru.baiganov.appfilm.api.ApiFactory.apiService

import ru.baiganov.appfilm.pojo.Movie
import ru.baiganov.appfilm.pojo.MoviePopular

private const val ORIGINAL = "original"

class NetworkMovieRepo() : MoviesRepository {
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
}