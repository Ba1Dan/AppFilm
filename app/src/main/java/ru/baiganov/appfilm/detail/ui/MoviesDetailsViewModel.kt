package ru.baiganov.appfilm.detail.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.baiganov.appfilm.detail.data.ActorsRepository
import ru.baiganov.appfilm.pojo.Actor
import ru.baiganov.appfilm.pojo.Movie

class MoviesDetailsViewModel(
        private val movie: Movie,
        private val repository: ActorsRepository
        ) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movies: LiveData<Movie> = _movie
    private val _actors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>> = _actors

    init {
        loadMovie()
    }

    private fun loadMovie() {
        viewModelScope.launch {
            _movie.value = movie
            val actorsList = repository.getActorsFromDatabase(movie.id)
            _actors.postValue(actorsList.actors)
        }
    }
}