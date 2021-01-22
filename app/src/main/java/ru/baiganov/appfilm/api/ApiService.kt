package ru.baiganov.appfilm.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.baiganov.appfilm.BuildConfig
import ru.baiganov.appfilm.pojo.Movie
import ru.baiganov.appfilm.pojo.MoviesData

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
            @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
            @Query(QUERY_PARAM_LANGUAGE) language: String = LANGUAGE,
            @Query(QUERY_PARAM_PAGE) page: Int = 1
            ) : MoviesData

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LANGUAGE = "language"
        private const val QUERY_PARAM_PAGE = "page"

        private const val API_KEY = BuildConfig.TMDB_API_KEY
        private const val LANGUAGE = "en-US"
    }
}