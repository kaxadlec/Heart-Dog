package com.google.android.horologist.datalayer.sample

import android.app.Application
import android.os.StrictMode
import androidx.work.Configuration
import com.google.android.horologist.datalayer.sample.screens.steps.CustomHiltWorkerFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class SampleApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: CustomHiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()


    override fun onCreate() {
        super.onCreate()
        setStrictMode()
    }

    private fun setStrictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectNetwork()
                .penaltyDeath()
                .build(),
        )
    }
}
