package ru.baiganov.appfilm.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import ru.baiganov.appfilm.pojo.Genre

class Converter {

    @TypeConverter
    fun fromGenre(genres: List<Genre>): String {
        return Gson().toJson(genres)
    }

    @TypeConverter
    fun toGenre(genresAsString: String): List<Genre> {
        val gson = Gson()
        val objects = gson.fromJson<List<Genre>>(genresAsString, List::class.java)
        val genres: MutableList<Genre> = mutableListOf()
        objects.forEach {
            genres.add(gson.fromJson(it.toString(), Genre::class.java))
        }
        return genres
    }
}