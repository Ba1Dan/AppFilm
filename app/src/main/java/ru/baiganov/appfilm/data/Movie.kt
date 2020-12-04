package ru.baiganov.appfilm.data

import android.graphics.drawable.Drawable

data class Movie(
        var poster: Int, // String
        var favourite: Boolean,
        var pg: String,
        var name: String,
        var tag: String,
        var reviews: String,
        var time: String
        )