package ru.baiganov.appfilm.detail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.baiganov.appfilm.data.Actor
import ru.baiganov.appfilm.data.Movie

class MoviesDetailsViewModel(private val movie: Movie) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movies: LiveData<Movie> = _movie
    private val _actors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>> = _actors

    init {
        loadMovie()
    }

    private fun loadMovie() {
        if (movies.value != null) {
            return
        }
        _movie.value = movie
        _actors.value = movie.actors
    }
}