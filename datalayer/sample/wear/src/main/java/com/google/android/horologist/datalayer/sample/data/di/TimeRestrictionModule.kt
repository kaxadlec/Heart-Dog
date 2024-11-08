// data/di/TimeRestrictionModule.kt
package com.google.android.horologist.datalayer.sample.data.di

import com.google.android.horologist.datalayer.sample.data.preferences.strategy.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TimeRestrictionModule {
    @Provides
    @Singleton
    fun provideTimeRestrictionStrategy(): TimeRestrictionStrategy {
        return TimeRestrictionStrategy()
    }
}