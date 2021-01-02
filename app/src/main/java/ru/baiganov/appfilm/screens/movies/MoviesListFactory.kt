package ru.baiganov.appfilm.screens.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.baiganov.appfilm.screens.MoviesRepository

class MoviesListFactory(private val repository: MoviesRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MoviesListViewModel::class.java -> MoviesListViewModel(repository)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}