package ru.baiganov.appfilm.list.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.baiganov.appfilm.list.repositories.MoviesRepository
import ru.baiganov.appfilm.pojo.Movie

class MoviesListViewModel(
        private val repository: MoviesRepository
        ) : ViewModel() {

    private val _mutableMovies = MutableLiveData<List<Movie>>(emptyList())
    private val _isLoading = MutableLiveData(true)
    private val _isNotifying = MutableLiveData(false)

    val movieList: LiveData<List<Movie>> = _mutableMovies
    val isLoading: LiveData<Boolean> = _isLoading
    val isNotifying: LiveData<Boolean> = _isNotifying //

    init {
        loadMovieJson()
    }

    private fun loadMovieJson() {
        viewModelScope.launch(Dispatchers.IO) {
            val temp = repository.getMovies()
            _isLoading.postValue(false)
            _mutableMovies.postValue(temp)
            repository.insertMovies(temp)
        }
    }
}