package ru.baiganov.appfilm.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.baiganov.appfilm.pojo.Actor
import ru.baiganov.appfilm.pojo.ActorsList

@Dao
interface ActorsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActors(actorsList: ActorsList)

    @Query("SELECT * FROM actors_list")
    suspend fun getActors(): List<ActorsList>

    @Query("SELECT * FROM actors_list WHERE id == :idMovie")
    suspend fun getActors(idMovie: Int): ActorsList
}