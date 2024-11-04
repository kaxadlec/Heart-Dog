package com.google.android.horologist.datalayer.sample.screens.steps

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters

interface ChildWorkerFactory {
    fun create(context: Context, workerParameters: WorkerParameters): ListenableWorker
}
