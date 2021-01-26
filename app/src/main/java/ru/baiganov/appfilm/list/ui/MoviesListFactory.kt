package ru.baiganov.appfilm.list.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.baiganov.appfilm.api.ApiService
import ru.baiganov.appfilm.list.data.MoviesRepository
import ru.baiganov.appfilm.list.data.NetworkMovieRepo

class MoviesListFactory(
    private val applicationContext: Context,
    private val apiService: ApiService
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MoviesListViewModel::class.java -> MoviesListViewModel(repository = NetworkMovieRepo(apiService, applicationContext) )
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}