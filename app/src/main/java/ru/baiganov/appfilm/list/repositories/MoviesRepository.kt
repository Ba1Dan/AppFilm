package ru.baiganov.appfilm.list.repositories

import androidx.lifecycle.LiveData
import ru.baiganov.appfilm.pojo.Movie

interface MoviesRepository {

    suspend fun getMovies(): LiveData<List<Movie>>

    suspend fun getMovie(id: Int): Movie

    suspend fun delete()

    suspend fun updateData()
}