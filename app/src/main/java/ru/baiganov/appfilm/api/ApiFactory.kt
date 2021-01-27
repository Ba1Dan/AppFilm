package ru.baiganov.appfilm.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

object ApiFactory {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private val contentType = "application/json".toMediaType()

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    @ExperimentalSerializationApi
    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(this.json.asConverterFactory(contentType))
            .build()

    @ExperimentalSerializationApi
    val apiService: ApiService = retrofit.create()
}