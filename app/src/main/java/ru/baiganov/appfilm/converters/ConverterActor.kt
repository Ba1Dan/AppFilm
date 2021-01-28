package ru.baiganov.appfilm.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.baiganov.appfilm.pojo.Actor
import ru.baiganov.appfilm.pojo.Genre

class ConverterActor {

    @TypeConverter
    fun fromGenre(actors: List<Actor>): String {
        return Gson().toJson(actors)
    }

    @TypeConverter
    fun toGenreList(actorsAsString: String): List<Actor> {
        val gson = Gson()
        val type = object : TypeToken<List<Actor>>() {}.type
        return gson.fromJson(actorsAsString, type)
    }
}