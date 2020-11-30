package ru.baiganov.appfilm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity(), FragmentMoviesDetails.ClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentMoviesList = FragmentMoviesList()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_main_activity, fragmentMoviesList)
            commit()
        }
    }

    fun onClickCard(view: View) {
        val fragmentMoviesDetails = FragmentMoviesDetails()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_main_activity, fragmentMoviesDetails)
            commit()
        }
    }

    override fun backToMovieList() {
       val fragmentMoviesList = FragmentMoviesList()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_main_activity, fragmentMoviesList)
            commit()
        }
    }
}