package com.google.android.horologist.datalayer.sample.screens.hotdog.vm


import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.NotificationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideNotificationRepository(): NotificationRepository {
        return NotificationRepository()
    }
}