package ru.baiganov.appfilm.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.baiganov.appfilm.pojo.Genre

class ConverterMovie {

    @TypeConverter
    fun fromGenre(genres: List<Genre>): String {
        return Gson().toJson(genres)
    }

    @TypeConverter
    fun toGenreList(genresAsString: String): List<Genre> {
        val gson = Gson()
        val type = object : TypeToken<List<Genre>>() {}.type
        return gson.fromJson(genresAsString, type)
    }
}