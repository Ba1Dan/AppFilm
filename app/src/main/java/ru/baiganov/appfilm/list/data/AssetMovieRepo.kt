package ru.baiganov.appfilm.list.data

import android.content.Context
import ru.baiganov.appfilm.data.Movie
import ru.baiganov.appfilm.data.loadMovies
import ru.baiganov.appfilm.list.data.MoviesRepository

class AssetMovieRepo(private val context: Context) : MoviesRepository {
    override suspend fun getMovies(): List<Movie> {
        return loadMovies(context)
    }
}