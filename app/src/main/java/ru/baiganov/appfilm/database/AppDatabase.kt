package ru.baiganov.appfilm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.baiganov.appfilm.pojo.ActorsList
import ru.baiganov.appfilm.pojo.Movie

@Database(entities = [Movie::class, ActorsList::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun create(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DB_NAME
                ).fallbackToDestructiveMigration()
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    abstract fun moviesDao(): MoviesDao
    abstract fun actorsDao(): ActorsDao
}