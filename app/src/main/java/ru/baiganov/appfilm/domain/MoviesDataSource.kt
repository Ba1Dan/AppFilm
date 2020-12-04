package ru.baiganov.appfilm.domain

import androidx.appcompat.content.res.AppCompatResources.getDrawable
import ru.baiganov.appfilm.R
import ru.baiganov.appfilm.data.Movie

class MoviesDataSource {
    fun getMovies(): List<Movie> {
        return listOf(
                Movie(R.drawable.bg_list, false, "13+", "Avengers: End Game", "Action, Adventure, Drama", "125 REVIEWS", "127 MIN"),
                Movie(R.drawable.tenet,true, "16+", "Tenet", "Action, Sci-Fi, Thriller", "98 REVIEWS", "97 MIN"),
                Movie(R.drawable.widow,true, "13+", "Black Widow", "Action, Adventure, Sci-Fi", "38 REVIEWS", "102 MIN"),
                Movie(R.drawable.woman, true, "13+", "Wonder Woman 1984", "Action, Adventure, Fantasy", "74 REVIEWS", "120 MIN")
        )
    }
}