package com.google.android.horologist.datalayer.sample

import android.app.Application
import android.os.StrictMode
import androidx.health.services.client.data.DataTypeAvailability
import androidx.health.services.client.data.SampleDataPoint
import androidx.work.Configuration
import com.google.android.horologist.datalayer.sample.screens.heartrate.data.HeartRateServicesRepository
import com.google.android.horologist.datalayer.sample.screens.heartrate.data.MeasureMessage
import com.google.android.horologist.datalayer.sample.screens.heartrate.presentation.HeartRateViewModel
import com.google.android.horologist.datalayer.sample.screens.steps.CustomHiltWorkerFactory
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.migration.CustomInjection.inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class SampleApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: CustomHiltWorkerFactory

    @Inject
    lateinit var heartRateRepository: HeartRateServicesRepository
    private val _heartRate = MutableStateFlow(0.0)
    val heartRate: StateFlow<Double> = _heartRate.asStateFlow()
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()


    override fun onCreate() {
        super.onCreate()
        setStrictMode()

        // 심박수 데이터 스트림을 전역적으로 수집
        collectHeartRateData()
    }

    private fun setStrictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectNetwork()
                .penaltyDeath()
                .build(),
        )
    }

    /**
     * 심박수 데이터를 수집하고 전역적으로 관리합니다.
     */
    private fun collectHeartRateData() {
        applicationScope.launch {
            val hasCapability = heartRateRepository.hasHeartRateCapability()
            if (hasCapability) {
                heartRateRepository.heartRateMeasureFlow().collect { message ->
                    when (message) {
                        is MeasureMessage.MeasureData -> {
                            val latestHeartRate = message.data.lastOrNull()?.value ?: 0.0
                            _heartRate.value = latestHeartRate
                            logHeartRateData(message.data)
                        }
                        is MeasureMessage.MeasureAvailability -> {
                            logHeartRateAvailability(message.availability)
                        }
                    }
                }
            } else {
                logDeviceNotSupported()
            }
        }
    }

    private fun logHeartRateAvailability(availability: DataTypeAvailability) {
        when (availability) {
            DataTypeAvailability.AVAILABLE -> {
                println("Heart Rate Sensor Available.")
            }
            DataTypeAvailability.UNAVAILABLE -> {
                println("Heart Rate Sensor Unavailable.")
            }
            else -> {
                println("Heart Rate Sensor Availability Unknown.")
            }
        }
    }

    private fun logHeartRateData(data: List<SampleDataPoint<Double>>) {
        data.forEach {
            println("Heart Rate Data: ${it.value}")
        }
    }

    private fun logDeviceNotSupported() {
        println("This device does not support heart rate measurement.")
    }
}
