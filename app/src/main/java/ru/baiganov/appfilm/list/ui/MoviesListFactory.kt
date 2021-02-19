package ru.baiganov.appfilm.list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.baiganov.appfilm.api.ApiService
import ru.baiganov.appfilm.database.MoviesDao
import ru.baiganov.appfilm.list.repositories.MovieRepo

class MoviesListFactory(
    private val apiService: ApiService,
    private val moviesDao: MoviesDao,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MoviesListViewModel::class.java -> MoviesListViewModel(
                repository  = MovieRepo(apiService = apiService, database = moviesDao))
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}