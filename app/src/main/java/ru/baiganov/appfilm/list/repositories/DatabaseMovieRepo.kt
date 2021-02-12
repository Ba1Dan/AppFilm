package ru.baiganov.appfilm.list.repositories

import ru.baiganov.appfilm.database.MoviesDao
import ru.baiganov.appfilm.pojo.Movie

class DatabaseMovieRepo(private val moviesDao: MoviesDao) : MoviesRepository {

    override suspend fun getMovies(): List<Movie> {
        return moviesDao.getMovies()
    }

    override suspend fun getMovie(id: Int): Movie {
        TODO("Not yet implemented")
    }

    override suspend fun insertMovies(moviesList: List<Movie>) {
        moviesDao.insertMovies(moviesList = moviesList)
    }

    override suspend fun deleteAll() {
        moviesDao.deleteAllMovies()
    }
}