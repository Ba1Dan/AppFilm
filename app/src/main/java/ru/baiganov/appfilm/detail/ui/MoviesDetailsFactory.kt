package ru.baiganov.appfilm.detail.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.baiganov.appfilm.api.ApiService
import ru.baiganov.appfilm.database.ActorsDao
import ru.baiganov.appfilm.detail.data.NetworkActorRepo
import ru.baiganov.appfilm.pojo.Movie

class MoviesDetailsFactory(
        private val movie: Movie,
        private val apiService: ApiService,
        private val actorsDao: ActorsDao
        ) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass){
        MoviesDetailsViewModel::class.java -> MoviesDetailsViewModel(movie, repository = NetworkActorRepo(
                apiService = apiService,
                actorsDao = actorsDao
        ))
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}