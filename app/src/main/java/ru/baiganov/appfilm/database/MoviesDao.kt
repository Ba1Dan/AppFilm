package ru.baiganov.appfilm.database

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.baiganov.appfilm.pojo.Actor
import ru.baiganov.appfilm.pojo.ActorsList
import ru.baiganov.appfilm.pojo.Movie

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies_list")
    fun getMovies(): LiveData<List<Movie>>

    @Query("DELETE FROM movies_list")
    suspend fun deleteAllMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(moviesList: List<Movie>)
}