package ru.baiganov.appfilm.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.baiganov.appfilm.BuildConfig
import ru.baiganov.appfilm.pojo.ActorsList
import ru.baiganov.appfilm.pojo.Configure
import ru.baiganov.appfilm.pojo.Movie
import ru.baiganov.appfilm.pojo.MoviesData

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
            @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
            @Query(QUERY_PARAM_LANGUAGE) language: String = LANGUAGE,
            @Query(QUERY_PARAM_PAGE) page: Int = 1
            ): MoviesData

    @GET("movie/{movie_id}")
    suspend fun getMovie(
            @Path(QUERY_PARAM_MOVIE_ID) id: Int?,
            @Query(QUERY_PARAM_API_KEY) api: String = API_KEY
    ): Movie

    @GET("configuration")
    suspend fun getConfig(@Query(QUERY_PARAM_API_KEY) api: String = API_KEY): Configure

    @GET("movie/{movie_id}/credits")
    suspend fun getActors(
            @Path(QUERY_PARAM_MOVIE_ID) id: Int?,
            @Query(QUERY_PARAM_API_KEY) api: String = API_KEY
    ): ActorsList

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LANGUAGE = "language"
        private const val QUERY_PARAM_PAGE = "page"
        private const val QUERY_PARAM_MOVIE_ID = "movie_id"

        private const val API_KEY = BuildConfig.TMDB_API_KEY
        private const val LANGUAGE = "en-US"
    }
}