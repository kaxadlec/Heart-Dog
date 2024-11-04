package com.google.android.horologist.datalayer.sample.screens.steps

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(StepCountWorker::class)
    abstract fun bindStepCountWorker(factory: StepCountWorker.Factory): ChildWorkerFactory
}
