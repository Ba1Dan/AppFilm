package ru.baiganov.appfilm.list.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.baiganov.appfilm.list.data.MoviesRepository
import ru.baiganov.appfilm.pojo.Movie

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
            Log.i("TEST", movies.toString())
            movieList.value = movies
        }
    }
}