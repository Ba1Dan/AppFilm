package ru.baiganov.appfilm.list.data

import ru.baiganov.appfilm.api.ApiFactory.apiService

import ru.baiganov.appfilm.pojo.Movie

class AssetMovieRepo() : MoviesRepository {
    override suspend fun getMovies(): List<Movie> {
        return apiService.getPopularMovies().movies
        //return loadMovies(context)
    }
}