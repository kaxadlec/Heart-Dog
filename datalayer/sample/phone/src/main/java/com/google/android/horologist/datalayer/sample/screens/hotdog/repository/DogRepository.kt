package com.google.android.horologist.datalayer.sample.screens.hotdog.repository

import android.util.Log
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.SupabaseClientProvider
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.Dog
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.Exp
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

@Serializable
data class DogDetails(
    val level: Int,
    val name: String,
    val current_exp: Long,
    val satiety: Int,
    val dog_position: Int,
    val max_exp: Long
)

class DogRepository {

    private val TAG = "DogRepository"

    @Serializable
    data class DogIdResponse(
        @SerialName("dog_id") val dogId: Long
    )

    suspend fun getDogIdByUserId(userId: Long): Long? = withContext(Dispatchers.IO) {
        try {
            val params = JsonObject(
                mapOf(
                    "p_user_id" to JsonPrimitive(userId)
                )
            )

            val response = SupabaseClientProvider.supabase
                .postgrest
                .rpc("get_dog_id_by_user_id", params)
                .decodeAs<DogIdResponse>()

            response.dogId
        } catch (e: Exception) {
            Log.e(TAG, "getDogIdByUserId 에러: ${e.message}", e)
            null
        }
    }

    suspend fun getDogDetailsById(dogId: Long): Dog? = withContext(Dispatchers.IO) {

        val params = JsonObject(
            mapOf("p_dog_id" to JsonPrimitive(dogId))
        )

        try {
            val result = SupabaseClientProvider.supabase
                .postgrest
                .rpc("get_dog_details_by_id", params)
                .decodeList<Dog>()
                .firstOrNull()

            result
        } catch (e: Exception) {
            Log.e(TAG, "getDogDetailsById 에러: ${e.message}", e)
            null
        }
    }

}
