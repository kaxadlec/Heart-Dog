package com.google.android.horologist.datalayer.sample.repository

import com.google.android.horologist.datalayer.sample.screens.hotdog.data.SupabaseClientProvider
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.User
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive


@OptIn(SupabaseExperimental::class)
class UserRepository {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                coerceInputValues = true
            })
        }
    }

    // 새 사용자를 users 테이블에 추가하는 함수
    suspend fun insertUser(email: String): Boolean = withContext(Dispatchers.IO) {
        val user = User(email = email)

        runCatching {
            println("Attempting to insert user: $user")

            SupabaseClientProvider.supabase
                .from("users")
                .insert(user)


            true
        }.getOrElse { e ->
            println("Error inserting user: ${e.localizedMessage}")
            e.printStackTrace()
            false
        }
    }

    // 이메일을 기준으로 사용자 정보를 가져오는 함수
    suspend fun getUserByEmail(email: String): User? = withContext(Dispatchers.IO) {
        try {
            val users = SupabaseClientProvider.supabase
                .from("users")
                .select()
                .decodeList<User>()

            val user = users.firstOrNull { it.email == email }
            println("Fetched user: $user")
            user

        } catch (e: Exception) {
            println("Error fetching user by email: ${e.message}")
            null
        }
    }

    suspend fun updateUserCode(userId: Long, code: String): Boolean = withContext(Dispatchers.IO) {
        runCatching {

            val params = JsonObject(
                mapOf(
                    "p_user_id" to JsonPrimitive(userId),
                    "new_code" to JsonPrimitive(code)
                )
            )

            val result = SupabaseClientProvider.supabase
                .postgrest
                .rpc("update_user_code", params)
                .decodeSingleOrNull<User>()

            if (result != null) {
                println("Code updated for userId: $userId")
                true
            } else {
                println("Failed to update code for userId: $userId")
                false
            }

        }.getOrElse { e ->
            println("Error updating code: ${e.localizedMessage}")
            e.printStackTrace()
            false
        }
    }

    @Serializable
    data class MatchData(
        @SerialName("scanned_user") val scannedUser: User,
        @SerialName("existing_user") val existingUser: User
    )

    @Serializable
    data class InsertCodeResult(
        val success: Boolean,
        val message: String? = null,
        val data: MatchData? = null,
        @SerialName("matched") val matched: Boolean = false
    )

    suspend fun insertScannedCode(userId: Long, scannedCode: String): InsertCodeResult = withContext(Dispatchers.IO) {
        try {
            val params = JsonObject(
                mapOf(
                    "p_user_id" to JsonPrimitive(userId),
                    "scanned_code" to JsonPrimitive(scannedCode)
                )
            )

            val response = SupabaseClientProvider.supabase
                .postgrest
                .rpc("insert_scanned_code", params)
                .decodeAs<InsertCodeResult>() // decodeAs 사용

            println("Response received: $response")
            response

        } catch (e: Exception) {
            println("Error in insertScannedCode: ${e.message}")
            println("JSON input: ${e.message}")
            InsertCodeResult(
                success = false,
                message = e.message ?: "Unknown error occurred",
                matched = false
            )
        }
    }

    suspend fun checkUserMatching(userId: Long): Boolean = withContext(Dispatchers.IO) {
        runCatching {
            val params = JsonObject(
                mapOf(
                    "p_user_id" to JsonPrimitive(userId)
                )
            )

            val result = SupabaseClientProvider.supabase
                .postgrest
                .rpc("check_user_matching", params)
                .decodeSingleOrNull<User>()

            result?.matching ?: false

        }.getOrElse { e ->
            println("Error checking matching status: ${e.message}")
            false
        }
    }
}