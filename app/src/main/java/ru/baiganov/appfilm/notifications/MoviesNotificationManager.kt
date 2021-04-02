package ru.baiganov.appfilm.notifications

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import ru.baiganov.appfilm.R
import ru.baiganov.appfilm.pojo.Movie

class MoviesNotificationManager(private val context: Context) {

        private val notificationManagerCompat: NotificationManagerCompat =
            NotificationManagerCompat.from(context)

        private fun initialize() {
            if (notificationManagerCompat.getNotificationChannel(CHANNEL_NEW_MESSAGES) == null) {

                val channel = NotificationChannelCompat.Builder(
                    CHANNEL_NEW_MESSAGES,
                    NotificationManagerCompat.IMPORTANCE_HIGH
                )
                    .setName("New movie")
                    .setDescription("New movie")
                    .build()
                notificationManagerCompat.createNotificationChannel(channel)
            }
        }

        fun showNotification(mostPopularMovie: Movie) {
            initialize()
            val uri =
                "ru.baiganov.appfilm://movies/movie/${mostPopularMovie.id}".toUri()
            val intent = Intent(Intent.ACTION_VIEW, uri)
            val pendingIntent = PendingIntent.getActivity(
                context,
                REQUEST_CONTENT,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            val notify = NotificationCompat.Builder(context, CHANNEL_NEW_MESSAGES)
                .setContentTitle(mostPopularMovie.title)
                .setContentText(mostPopularMovie.overview)
                .setSmallIcon(R.drawable.star_on)
                .setPriority(NotificationManagerCompat.IMPORTANCE_HIGH)
                .setContentIntent(pendingIntent)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(mostPopularMovie.overview)
                )
                .build()
            notificationManagerCompat.notify(MOVIE_TAG, mostPopularMovie.id, notify)
        }

        fun dismissNotification(movieId: Int) {
            notificationManagerCompat.cancel(MOVIE_TAG, movieId)

        }

        companion object {
            private const val REQUEST_CONTENT = 1
            private const val CHANNEL_NEW_MESSAGES = "new_movie"
            private const val MOVIE_TAG = "movie"
        }
}