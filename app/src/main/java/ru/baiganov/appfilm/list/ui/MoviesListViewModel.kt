package ru.baiganov.appfilm.list.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.baiganov.appfilm.data.Movie
import ru.baiganov.appfilm.list.data.MoviesRepository

class MoviesListViewModel (private val repository: MoviesRepository) : ViewModel() {

    private val _mutableMovies = MutableLiveData<List<Movie>>(emptyList())
    val movieList get() = _mutableMovies

    init {
        loadMovieJson()
    }

    private fun loadMovieJson() {
        if (movieList.value == null) {
            return
        }
        viewModelScope.launch {
            val movies = repository.getMovies()
            movieList.value = movies
        }
    }
}