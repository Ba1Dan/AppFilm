package ru.baiganov.appfilm.list.ui

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.baiganov.appfilm.detail.data.ActorsRepository
import ru.baiganov.appfilm.list.repositories.MoviesRepository
import ru.baiganov.appfilm.pojo.Movie

class MoviesListViewModel(
        private val moviesRepository: MoviesRepository,
        private val actorsRepository: ActorsRepository,
        ) : ViewModel() {

    private val _mutableMovies = MutableLiveData<List<Movie>>(emptyList())
    private val _isLoading = MutableLiveData(true)

    val movieList: LiveData<List<Movie>> = _mutableMovies
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadMovieJson()
    }

    private fun loadMovieJson() {
        viewModelScope.launch(Dispatchers.IO) {
            val temp = moviesRepository.getMovies()
            _isLoading.postValue(false)
            _mutableMovies.postValue(temp)
            moviesRepository.insertMovies(temp)
        }
    }

    private suspend fun insertActorsInDatabase(movies: List<Movie>) {
        movies.forEach {
            actorsRepository.insertActors(actorsRepository.getActors(it.id))
        }
    }
}