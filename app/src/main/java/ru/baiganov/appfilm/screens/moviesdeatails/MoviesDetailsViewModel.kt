package ru.baiganov.appfilm.screens.moviesdeatails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.baiganov.appfilm.data.Actor
import ru.baiganov.appfilm.data.Movie

class MoviesDetailsViewModel(private val movies: Movie) :
        ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie
    private val _actors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>> = _actors

    init {
        loadMovie()
    }

    private fun loadMovie() {
        if (movie.value != null) {
            return
        }
        _movie.value = movies
        _actors.value = movie.value?.actors
    }
}