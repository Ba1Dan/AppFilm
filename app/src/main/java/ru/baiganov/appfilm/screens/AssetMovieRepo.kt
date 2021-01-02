package ru.baiganov.appfilm.screens

import android.content.Context
import ru.baiganov.appfilm.data.Movie
import ru.baiganov.appfilm.data.loadMovies

class AssetMovieRepo(private val context: Context) : MoviesRepository {
    override suspend fun getMovies(): List<Movie> {
        return loadMovies(context)
    }
}