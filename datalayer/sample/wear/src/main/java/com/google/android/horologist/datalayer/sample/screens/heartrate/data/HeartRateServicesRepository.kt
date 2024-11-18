package com.google.android.horologist.datalayer.sample.screens.heartrate.data

import android.content.Context
import android.util.Log
import androidx.concurrent.futures.await
import androidx.health.services.client.HealthServices
import androidx.health.services.client.MeasureCallback
import androidx.health.services.client.data.Availability
import androidx.health.services.client.data.DataPointContainer
import androidx.health.services.client.data.DataType
import androidx.health.services.client.data.DataTypeAvailability
import androidx.health.services.client.data.DeltaDataType
import androidx.health.services.client.data.SampleDataPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.runBlocking

private const val TAG = "HeartRateServicesRepository"

/**
 * Repository to interact with Health Services APIs to collect heart rate data.
 */
class HeartRateServicesRepository @Inject constructor(
    @ApplicationContext context: Context
) {
    private val healthServicesClient = HealthServices.getClient(context)
    private val measureClient = healthServicesClient.measureClient

    /**
     * Check if the device supports heart rate measurements.
     */
    suspend fun hasHeartRateCapability(): Boolean {
        val capabilities = measureClient.getCapabilitiesAsync().await()
        return DataType.HEART_RATE_BPM in capabilities.supportedDataTypesMeasure
    }

    /**
     * Returns a Flow to collect heart rate data.
     * It registers a callback and emits data points as they are received.
     */
    fun heartRateMeasureFlow() = callbackFlow {
        val callback = object : MeasureCallback {
            override fun onAvailabilityChanged(
                dataType: DeltaDataType<*, *>,
                availability: Availability
            ) {
                // Only send back DataTypeAvailability (ignore LocationAvailability)
                if (availability is DataTypeAvailability) {
                    trySendBlocking(MeasureMessage.MeasureAvailability(availability))
                }
            }

            override fun onDataReceived(data: DataPointContainer) {
                val heartRateBpm = data.getData(DataType.HEART_RATE_BPM)
                if (heartRateBpm.isNotEmpty()) {
                    Log.d(TAG, "Heart rate data received: $heartRateBpm")
                    trySendBlocking(MeasureMessage.MeasureData(heartRateBpm))
                } else {
                    Log.d(TAG, "Empty heart rate data received")
                }
            }

        }

        // Register the callback to start receiving heart rate data
        Log.d(TAG, "Registering heart rate callback")
        measureClient.registerMeasureCallback(DataType.HEART_RATE_BPM, callback)

        // Clean up callback when the flow collector is cancelled
        awaitClose {
            Log.d(TAG, "Unregistering heart rate callback")
            runBlocking {
                measureClient.unregisterMeasureCallbackAsync(DataType.HEART_RATE_BPM, callback).await()
            }
        }
    }
}

/**
 * Sealed class to handle different types of heart rate messages.
 */
sealed class MeasureMessage {
    data class MeasureAvailability(val availability: DataTypeAvailability) : MeasureMessage()
    data class MeasureData(val data: List<SampleDataPoint<Double>>) : MeasureMessage()
}
