package ru.baiganov.appfilm.list.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.baiganov.appfilm.api.ApiFactory.apiService
import ru.baiganov.appfilm.api.ApiService
import ru.baiganov.appfilm.database.AppDatabase
import ru.baiganov.appfilm.pojo.Actor
import ru.baiganov.appfilm.pojo.ActorsList

import ru.baiganov.appfilm.pojo.Movie
import ru.baiganov.appfilm.pojo.MoviePopular

private const val ORIGINAL = "original"

class NetworkMovieRepo(
    private val apiService: ApiService,
    private val context: Context
) : MoviesRepository {

    private val database = AppDatabase.create(context)

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

    override suspend fun getActorsFromNetwork(id: Int): ActorsList {
        val actors = apiService.getActors(id).actors
        val imageBaseUrl = apiService.getConfig().images.baseUrl + ORIGINAL
        actors.map {
            val fullUrl = imageBaseUrl + it.actorImageUrl
            it.actorImageUrl = fullUrl
        }
        return ActorsList(id, actors)
    }

    override suspend fun getMoviesFromDatabase() : List<Movie> = withContext(Dispatchers.IO) {
        return@withContext database.moviesDao().getMovies()
    }

    override suspend fun insertDataInDatabase(moviesList: List<Movie>) = withContext(Dispatchers.IO) {
        database.moviesDao().insertMovies(moviesList)
    }

    override suspend fun insertActorsInDatabase(actorsList: ActorsList) {
        database.actorsDao().insertActors(actorsList = actorsList)
    }

    override fun checkNetwork(): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}