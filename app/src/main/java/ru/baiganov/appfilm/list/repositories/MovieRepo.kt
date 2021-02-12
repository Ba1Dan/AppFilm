package ru.baiganov.appfilm.list.repositories

import retrofit2.HttpException
import ru.baiganov.appfilm.pojo.Movie

class MovieRepo(
        private val networkRepo: NetworkMovieRepo,
        private val databaseRepo: DatabaseMovieRepo
) : MoviesRepository {

    private var network: Boolean = true
    override suspend fun getMovies(): List<Movie> {
        try {
            return networkRepo.getMovies()
        } catch (e: Exception) {
            network = false
            return databaseRepo.getMovies()
        }
    }

    suspend fun checkNetwork(): Boolean {
        return network
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