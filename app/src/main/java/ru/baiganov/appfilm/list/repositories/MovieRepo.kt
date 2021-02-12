package ru.baiganov.appfilm.list.repositories

import android.util.Log
import retrofit2.HttpException
import ru.baiganov.appfilm.pojo.Movie
import ru.baiganov.appfilm.utils.checkNetwork

class MovieRepo(
        private val networkRepo: NetworkMovieRepo,
        private val databaseRepo: DatabaseMovieRepo
) : MoviesRepository {

    override suspend fun getMovies(): List<Movie> {
        return try {
            networkRepo.getMovies()
        } catch (e: HttpException) {
            databaseRepo.getMovies()
        }
    }

    override suspend fun getMovie(id: Int): Movie {
        return networkRepo.getMovie(id)
    }

    override suspend fun insertMovies(moviesList: List<Movie>) {
       databaseRepo.insertMovies(moviesList)
    }

    override suspend fun deleteAll() {
        databaseRepo.deleteAll();
    }
}