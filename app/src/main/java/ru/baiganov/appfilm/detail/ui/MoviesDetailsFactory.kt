package ru.baiganov.appfilm.detail.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.baiganov.appfilm.api.ApiService
import ru.baiganov.appfilm.detail.data.ActorsRepository
import ru.baiganov.appfilm.detail.data.NetworkActorRepo
import ru.baiganov.appfilm.pojo.Movie

class MoviesDetailsFactory(
        private val movie: Movie,
        private val applicationContext: Context,
        ) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass){
        MoviesDetailsViewModel::class.java -> MoviesDetailsViewModel(movie, repository = NetworkActorRepo(applicationContext))
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}