package ru.baiganov.appfilm.list.data

import ru.baiganov.appfilm.api.ApiService
import ru.baiganov.appfilm.database.ActorsDao
import ru.baiganov.appfilm.database.MoviesDao
import ru.baiganov.appfilm.pojo.ActorsList
import ru.baiganov.appfilm.pojo.Movie

private const val ORIGINAL = "original"

class NetworkMovieRepo(
    private val apiService: ApiService,
    private val moviesDao: MoviesDao,
) : MoviesRepository {

    override suspend fun deleteAll() {
        moviesDao.deleteAllMovies()
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

    override suspend fun getMoviesFromDatabase(): List<Movie>  {
        return moviesDao.getMovies()
    }

    override suspend fun insertMovies(moviesList: List<Movie>) {
        moviesDao.insertMovies(moviesList = moviesList)
    }
}