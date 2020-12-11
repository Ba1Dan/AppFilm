package ru.baiganov.appfilm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.baiganov.appfilm.fragments.FragmentMoviesList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentMoviesList = FragmentMoviesList()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_main_activity, fragmentMoviesList)
            addToBackStack(null)
            commit()
        }
    }
}