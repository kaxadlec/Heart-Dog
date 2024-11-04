package com.google.android.horologist.datalayer.sample.screens.steps

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class CustomHiltWorkerFactory @Inject constructor(
    private val workerFactories: Map<Class<out ListenableWorker>, @JvmSuppressWildcards Provider<ChildWorkerFactory>>
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        Log.d("CustomWorkerFactory", "Attempting to create worker: $workerClassName")
        val foundEntry = workerFactories.entries.find { Class.forName(workerClassName).isAssignableFrom(it.key) }
        val factoryProvider = foundEntry?.value ?: return null
        return factoryProvider.get().create(appContext, workerParameters)
    }
}
