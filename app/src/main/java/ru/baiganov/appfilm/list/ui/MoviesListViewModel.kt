package ru.baiganov.appfilm.list.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.baiganov.appfilm.list.data.MoviesRepository
import ru.baiganov.appfilm.pojo.Movie
import ru.baiganov.appfilm.utlils.checkNetwork

class MoviesListViewModel(
        private val repository: MoviesRepository,
        application: Application
        ) : AndroidViewModel(application) {

    private val _mutableMovies = MutableLiveData<List<Movie>>(emptyList())
    private val _isLoading = MutableLiveData(false)

    val movieList: LiveData<List<Movie>> = _mutableMovies
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadMovieJson()
    }

    private fun loadMovieJson() {
        viewModelScope.launch {
            //repository.deleteAll()
            val temp = repository.getMoviesFromDatabase()
            Log.i("INFO_MOVIES_DATABASE", temp.toString())
            _mutableMovies.postValue(temp)

            if(checkNetwork(getApplication())) {
                val movies = repository.getMovies()
                Log.i("INFO_MOVIES_NETWORK", movies.toString())
                insertActorsInDatabase(movies)
                _mutableMovies.postValue(movies)
                repository.insertMovies(movies)
            } else {
                _isLoading.postValue(true)
            }
        }
    }

    private suspend fun insertActorsInDatabase(movies: List<Movie>) {
        movies.forEach {
            repository.insertActors(repository.getActors(it.id))
        }
    }
}