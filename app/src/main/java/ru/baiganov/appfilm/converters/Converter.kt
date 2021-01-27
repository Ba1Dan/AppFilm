package ru.baiganov.appfilm.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.baiganov.appfilm.pojo.Genre
import java.lang.reflect.Type

class Converter {

    @TypeConverter
    fun fromGenre(genres: List<Genre>): String {
        return Gson().toJson(genres)
    }


    /*fun toGenre(genresAsString: String): List<Genre> {
        val objects = Gson().fromJson<List<Genre>>(genresAsString, List::class.java)
        val genres: MutableList<Genre> = mutableListOf()
        objects.forEach {
            genres.add(Gson().fromJson(it.toString(), Genre::class.java))
        }
        return genres
    }*/


    @TypeConverter
    fun toGenreList(genresAsString: String): List<Genre> {
        val gson = Gson()
        val type = object : TypeToken<List<Genre>>() {}.type
        return gson.fromJson(genresAsString, type)
    }
}