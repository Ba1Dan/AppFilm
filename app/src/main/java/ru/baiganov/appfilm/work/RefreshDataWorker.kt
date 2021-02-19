package ru.baiganov.appfilm.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.bumptech.glide.load.HttpException
import ru.baiganov.appfilm.api.ApiFactory
import ru.baiganov.appfilm.database.AppDatabase
import ru.baiganov.appfilm.detail.repositories.ActorRepo
import ru.baiganov.appfilm.detail.repositories.ActorsRepository
import ru.baiganov.appfilm.detail.repositories.DatabaseActorRepo
import ru.baiganov.appfilm.detail.repositories.NetworkActorRepo
import ru.baiganov.appfilm.list.repositories.MovieRepo
import ru.baiganov.appfilm.list.repositories.MoviesRepository

class RefreshDataWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        val database: AppDatabase = AppDatabase.create(applicationContext)
        val api = ApiFactory.apiService
        val repositoryMovie: MoviesRepository = MovieRepo(apiService = api, database = database.moviesDao())
        val repositoryActor: ActorsRepository = ActorRepo(NetworkActorRepo(api), DatabaseActorRepo(database.actorsDao()))

        try {
            repositoryMovie.refreshMovies()
        } catch (e: HttpException) {
            return Result.retry()
        }
        return  Result.success()
    }

    companion object {
        const val WORK_NAME = "ru.baiganov.appfilm.work.RefreshDataWorker"
    }
}