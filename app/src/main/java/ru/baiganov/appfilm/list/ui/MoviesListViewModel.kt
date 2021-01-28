package ru.baiganov.appfilm.list.ui

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.baiganov.appfilm.list.data.MoviesRepository
import ru.baiganov.appfilm.pojo.Movie

class MoviesListViewModel (private val repository: MoviesRepository) : ViewModel() {

    private val _mutableMovies = MutableLiveData<List<Movie>>(emptyList())
    private val _isLoading = MutableLiveData(false)
    private val internet = true

    val movieList: LiveData<List<Movie>> = _mutableMovies
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadMovieJson()
    }

    private fun loadMovieJson() {
        viewModelScope.launch {
            _mutableMovies.value = repository.getMoviesFromDatabase()
            if(repository.checkNetwork()) {
                val movies = repository.getMovies()
                insertActorsInDatabase(movies)
                _mutableMovies.postValue(movies)
                repository.insertDataInDatabase(movies)
            }
        }
    }

    private fun insertActorsInDatabase(movies: List<Movie>) {
        viewModelScope.launch {
            movies.forEach {
                repository.insertActorsInDatabase(repository.getActorsFromNetwork(it.id))
            }
        }
    }
}