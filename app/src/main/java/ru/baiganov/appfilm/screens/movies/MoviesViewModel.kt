package ru.baiganov.appfilm.screens.movies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.baiganov.appfilm.data.Movie
import ru.baiganov.appfilm.data.loadMovies

class MoviesViewModel(application: Application): AndroidViewModel(application) {

    private var mutableMovies = MutableLiveData<List<Movie>>(emptyList())
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    val movies: LiveData<List<Movie>> get() = mutableMovies

    init {
        loadData()
    }

    private fun loadData() {
        scope.launch {
            val movies = loadMovies(getApplication())
            mutableMovies.postValue(movies)
        }
    }
}