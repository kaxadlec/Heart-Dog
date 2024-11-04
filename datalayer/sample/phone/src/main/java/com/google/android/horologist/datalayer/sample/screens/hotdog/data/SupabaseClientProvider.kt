package com.google.android.horologist.datalayer.sample.screens.hotdog.data

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import kotlinx.serialization.json.Json
import io.github.jan.supabase.postgrest.postgrest

object SupabaseClientProvider {

    val supabase = createSupabaseClient(
        supabaseUrl = "https://kohyabpcmxjglxgewwau.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImtvaHlhYnBjbXhqZ2x4Z2V3d2F1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzA0Mzk5MzAsImV4cCI6MjA0NjAxNTkzMH0.EOB6RObjQWTsLU2mBAhG6ye1QuUSdnMEaOXre8AsvLw"
    ) {
        install(Postgrest) {
            serializer = KotlinXSerializer(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    coerceInputValues = true // null 값을 기본값으로 대체
                }
            )
        }
    }
}
