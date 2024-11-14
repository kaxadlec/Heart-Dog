package com.google.android.horologist.datalayer.sample.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.horologist.datalayer.sample.grpc.LocationTrackingService
import com.google.android.horologist.datalayer.sample.shared.grpc.LocationTrackingProto
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationTrackingModule {

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(
        @ApplicationContext applicationContext: Context
    ): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(applicationContext)
    }

    @Singleton
    @Provides
    fun provideLocationTrackingService(
        dataStore: DataStore<LocationTrackingProto.LocationTrackingRecord>
    ): LocationTrackingService {
        return LocationTrackingService(dataStore)
    }

}