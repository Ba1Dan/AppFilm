package ru.baiganov.appfilm.list.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.baiganov.appfilm.list.data.MoviesRepository
import ru.baiganov.appfilm.pojo.Movie

class MoviesListViewModel (private val repository: MoviesRepository) : ViewModel() {

    private val _mutableMovies = MutableLiveData<List<Movie>>(emptyList())
    private val _isLoading = MutableLiveData(false)

    val movieList: LiveData<List<Movie>> = _mutableMovies
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadMovieJson()
    }

    private fun loadMovieJson() {
        viewModelScope.launch {
            _isLoading.value = true
            val movies = repository.getMovies()
            _isLoading.value = false
            _mutableMovies.value = movies
        }
    }
}