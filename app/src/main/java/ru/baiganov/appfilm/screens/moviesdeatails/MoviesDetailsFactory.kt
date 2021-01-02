package ru.baiganov.appfilm.screens.moviesdeatails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.baiganov.appfilm.data.Movie

class MoviesDetailsFactory(private val movie: Movie) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass){
        MoviesDetailsViewModel::class.java -> MoviesDetailsViewModel(movie)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}