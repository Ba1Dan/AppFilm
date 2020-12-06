package ru.baiganov.appfilm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView

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

    fun onClickCard(view: View) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_main_activity,  FragmentMoviesDetails())
            addToBackStack(null)
            commit()
        }
    }
}