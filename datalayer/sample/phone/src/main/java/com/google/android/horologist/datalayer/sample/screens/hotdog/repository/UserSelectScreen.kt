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
import com.google.android.horologist.datalayer.sample.repository.UserRepository
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.User

@Composable
fun UserSelectScreen(userRepository: UserRepository) {
    var userEmail by remember { mutableStateOf("") }
    var userInfo by remember { mutableStateOf<User?>(null) }
    var selectStatus by remember { mutableStateOf("") }

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
            label = { Text("Enter User Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    val user = userRepository.getUserByEmail(userEmail)
                    if (user != null) {
                        userInfo = user
                        selectStatus = "User found!"
                    } else {
                        selectStatus = "User not found."
                        userInfo = null
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Select User")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = selectStatus)

        userInfo?.let { user ->
            Spacer(modifier = Modifier.height(16.dp))
            Text("Email: ${user.email}")
            Text("Code: ${user.code}")
            Text("Matching: ${user.matching}")
            Text("Token: ${user.token}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserSelectScreenPreview() {
    UserSelectScreen(userRepository = UserRepository())
}
