package com.google.android.horologist.datalayer.sample.data.di

import android.content.Context
import com.google.android.horologist.datalayer.sample.data.preferences.FeedingPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideFeedingPreferences(
        @ApplicationContext context: Context
    ): FeedingPreferences {
        return FeedingPreferences(context)
    }
}