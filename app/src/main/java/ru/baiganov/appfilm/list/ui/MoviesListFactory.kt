package ru.baiganov.appfilm.list.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.baiganov.appfilm.api.ApiService
import ru.baiganov.appfilm.database.ActorsDao
import ru.baiganov.appfilm.database.MoviesDao
import ru.baiganov.appfilm.list.data.NetworkMovieRepo

class MoviesListFactory(
    private val apiService: ApiService,
    private val moviesDao: MoviesDao,
    private val actorsDao: ActorsDao,
    private val application: Application
) : ViewModelProvider.AndroidViewModelFactory(application) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MoviesListViewModel::class.java -> MoviesListViewModel(repository = NetworkMovieRepo(
                apiService = apiService,
                moviesDao = moviesDao,
                actorsDao = actorsDao
        ),
        application = application)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}