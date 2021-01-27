package ru.baiganov.appfilm.database

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.baiganov.appfilm.pojo.Movie

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies_list")
    fun getData(): List<Movie>

    @Query("DELETE FROM movies_list")
    fun deleteAllData()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(moviesList: List<Movie>)
}