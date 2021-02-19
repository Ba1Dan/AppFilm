package ru.baiganov.appfilm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.WorkManager
import ru.baiganov.appfilm.list.ui.FragmentMoviesList
import ru.baiganov.appfilm.work.DataSchedule

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
        DataSchedule(WorkManager.getInstance()).schedule()
    }
}