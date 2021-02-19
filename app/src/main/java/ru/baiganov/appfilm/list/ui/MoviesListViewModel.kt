package ru.baiganov.appfilm.list.ui

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.baiganov.appfilm.list.repositories.MoviesRepository
import ru.baiganov.appfilm.pojo.Movie
import kotlin.RuntimeException

class MoviesListViewModel(
        private val repository: MoviesRepository
        ) : ViewModel() {

    private val _mutableMovies = MutableLiveData<List<Movie>>()
    private val _isNotifying = MutableLiveData(false)

    val movieList: LiveData<List<Movie>> = _mutableMovies
    val isNotifying: LiveData<Boolean> = _isNotifying

    init {
        loadMovieJson()
    }

    private fun loadMovieJson() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = repository.getMovies()
                withContext(Dispatchers.Main) {
                    data.observeForever(Observer {
                        _mutableMovies.postValue(it)
                    })
                }
            } catch (e: RuntimeException) {
                e.printStackTrace()
                _isNotifying.postValue(true)
            }
        }
    }
}