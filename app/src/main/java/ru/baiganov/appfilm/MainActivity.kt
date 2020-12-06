package ru.baiganov.appfilm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.baiganov.appfilm.fragments.FragmentMoviesDetails
import ru.baiganov.appfilm.fragments.FragmentMoviesList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_main_activity, FragmentMoviesList())
                addToBackStack(null)
                commit()
            }
        }
    }
}