package com.google.android.horologist.datalayer.sample.screens.hotdog.repository

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.google.android.horologist.datalayer.sample.repository.UserRepository

@Composable
fun UserInsertScreen(userRepository: UserRepository) {
    var userEmail by remember { mutableStateOf("") }
    var insertStatus by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = userEmail,
            onValueChange = { userEmail = it },
            label = { Text("User Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    val success = userRepository.insertUser(userEmail)
                    withContext(Dispatchers.Main) {
                        insertStatus = if (success) "User inserted successfully!" else "Failed to insert user."
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Insert User")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = insertStatus)
    }
}
