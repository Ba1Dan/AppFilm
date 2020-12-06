package ru.baiganov.appfilm.domain

import android.content.Context
import android.provider.Settings.Secure.getString
import ru.baiganov.appfilm.data.Actor
import ru.baiganov.appfilm.R

class ActorsDataSource {
    fun getActors(context: Context):List<Actor> {
        return listOf(
                Actor(context.getString(R.string.robert_downey_jr), R.drawable.robert),
                Actor(context.getString(R.string.mark_ruffalo), R.drawable.ruffalo),
                Actor(context.getString(R.string.chris_evans), R.drawable.chris_evans),
                Actor(context.getString(R.string.chris_hemsworth), R.drawable.chris_hemsworth)
        )
    }
}