package com.udacity.asteroidradar

import android.app.Application
import android.os.Build
import androidx.work.*
import com.udacity.asteroidradar.work.FetchAsteroidsWork
import com.udacity.asteroidradar.work.FetchAsteroidsWork.Companion.WORK_NAME
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class AsteroidApp : Application() {
    private val applcationScope = CoroutineScope(Dispatchers.Default)

    private fun delayedInit() {
        applcationScope.launch {
            setupRecurringWork()
        }
    }

    private fun setupRecurringWork() {
        val constraint = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.UNMETERED).apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setRequiresDeviceIdle(true)
                }
            }.build()

        val dailyRequest =
            PeriodicWorkRequestBuilder<FetchAsteroidsWork>(1, TimeUnit.DAYS)
                .setConstraints(constraint)
                .build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(WORK_NAME, ExistingPeriodicWorkPolicy.KEEP, dailyRequest)
    }

    override fun onCreate() {
        super.onCreate()
        delayedInit()
    }
}