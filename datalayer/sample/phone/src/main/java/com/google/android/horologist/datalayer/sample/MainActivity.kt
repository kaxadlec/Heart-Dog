/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.horologist.datalayer.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.android.horologist.datalayer.sample.screens.main.MainScreen
import com.google.android.horologist.datalayer.sample.ui.theme.HorologistTheme
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.websocket.WebSocketDeflateExtension.Companion.install
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

private val supabase = createSupabaseClient(
    supabaseUrl = "https://uajngryuubmkjmxxahsm.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InVham5ncnl1dWJta2pteHhhaHNtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzAxNjc4OTEsImV4cCI6MjA0NTc0Mzg5MX0.78sdA7CTrEbRUbZG-Rz73CcP6yWqs845QJZbnzXznKI"
) {
    install(Postgrest)
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HorologistTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MainScreen()
                }
            }
        }
    }
}