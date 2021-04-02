package ru.baiganov.appfilm.work

import androidx.work.*
import java.util.concurrent.TimeUnit

class DataSchedule(private val workManager: WorkManager) {

    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresCharging(true)
        .build()

    private val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(8, TimeUnit.HOURS)
        .setConstraints(constraints)
        .build()

    fun schedule() {
        workManager.enqueueUniquePeriodicWork(RefreshDataWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest)
    }
}