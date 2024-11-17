package com.google.android.horologist.datalayer.sample.screens.hotdog.repository

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import android.util.Log
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.SupabaseClientProvider
import javax.inject.Inject

@Serializable
data class Location(
    val latitude: Double,
    val longitude: Double,
    val altitude: Double
)

@Serializable
data class LocationUpdateResponse(
    val success: Boolean,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val altitude: Double? = null
)

@Serializable
data class MatchingCheckResponse(
    val success: Boolean,
    val matched: Boolean? = null,
    val distance: Double? = null,
    @SerialName("partner_id") val partnerId: Long? = null,
    val message: String? = null
)

class LocationRepository @Inject constructor() {
    private val TAG = "LocationRepository"

    suspend fun updateLocation(
        userId: Long,
        latitude: Double,
        longitude: Double,
        altitude: Double
    ): LocationUpdateResponse? = withContext(Dispatchers.IO) {
        try {
            val params = JsonObject(
                mapOf(
                    "p_user_id" to JsonPrimitive(userId),
                    "p_latitude" to JsonPrimitive(latitude),
                    "p_longitude" to JsonPrimitive(longitude),
                    "p_altitude" to JsonPrimitive(altitude)
                )
            )

            SupabaseClientProvider.supabase
                .postgrest
                .rpc("update_user_location", params)
                .decodeAs<LocationUpdateResponse>()
        } catch (e: Exception) {
            Log.e(TAG, "위치 업데이트 에러: ${e.message}", e)
            null
        }
    }

    suspend fun checkMatching(userId: Long): MatchingCheckResponse? = withContext(Dispatchers.IO) {
        try {
            val params = JsonObject(
                mapOf("p_user_id" to JsonPrimitive(userId))
            )

            val response = SupabaseClientProvider.supabase
                .postgrest
                .rpc("check_matching_status", params)
                .decodeAs<MatchingCheckResponse>()

            // 매칭 결과에 따른 처리
            response?.let {
                if (it.success) {
                    if (it.matched == true) {
                        Log.d(TAG, "매칭 성공! 거리: ${it.distance}m")
                    } else {
                        Log.d(TAG, "매칭 실패! 거리: ${it.distance}m")
                    }
                }
            }

            response
        } catch (e: Exception) {
            Log.e(TAG, "매칭 체크 에러: ${e.message}", e)
            null
        }
    }
}