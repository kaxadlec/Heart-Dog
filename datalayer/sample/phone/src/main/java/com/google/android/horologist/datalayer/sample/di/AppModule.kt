package com.google.android.horologist.datalayer.sample.di

import android.content.Context
import com.google.android.horologist.datalayer.sample.repository.UserRepository
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.manager.UserSessionManager
import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.DogRepository
import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.NotificationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    // DogRepository 제공
    @Provides
    @Singleton
    fun provideDogRepository(): DogRepository {
        return DogRepository()
    }

    // UserRepository 제공
    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository {
        return UserRepository()
    }

    // UserSessionManager 제공
    @Provides
    @Singleton
    fun provideUserSessionManager(@ApplicationContext context: Context): UserSessionManager {
        return UserSessionManager(context)
    }
}