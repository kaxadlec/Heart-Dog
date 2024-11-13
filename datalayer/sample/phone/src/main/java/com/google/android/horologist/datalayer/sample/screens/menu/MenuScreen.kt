package com.google.android.horologist.datalayer.sample.screens.menu

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.android.horologist.datalayer.sample.R
import com.google.android.horologist.datalayer.sample.screens.ApiTest
import com.google.android.horologist.datalayer.sample.screens.AppHelperNodes
import com.google.android.horologist.datalayer.sample.screens.AppHelperNodesListener
import com.google.android.horologist.datalayer.sample.screens.Counter
import com.google.android.horologist.datalayer.sample.screens.EmojiTest
import com.google.android.horologist.datalayer.sample.screens.HeartRate
import com.google.android.horologist.datalayer.sample.screens.HotDogMain
import com.google.android.horologist.datalayer.sample.screens.InstallAppCustomPromptDemo
import com.google.android.horologist.datalayer.sample.screens.InstallAppPromptDemo
import com.google.android.horologist.datalayer.sample.screens.InstallTileCustomPromptDemo
import com.google.android.horologist.datalayer.sample.screens.InstallTilePromptDemo
import com.google.android.horologist.datalayer.sample.screens.Matching
import com.google.android.horologist.datalayer.sample.screens.ReEngageCustomPromptDemo
import com.google.android.horologist.datalayer.sample.screens.ReEngagePromptDemo
import com.google.android.horologist.datalayer.sample.screens.SignInCustomPromptDemo
import com.google.android.horologist.datalayer.sample.screens.SignInPromptDemo
import com.google.android.horologist.datalayer.sample.screens.StepCount
import com.google.android.horologist.datalayer.sample.screens.Splash
import com.google.android.horologist.datalayer.sample.screens.hotdog.login.viewmodel.SignInViewModel
import io.github.jan.supabase.auth.status.SessionStatus

@Composable
fun MenuScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val signInViewModel: SignInViewModel = hiltViewModel()
    val sessionStatus by signInViewModel.sessionStatus.collectAsState()

    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        item {
            Button(
                onClick = { navController.navigate(ApiTest) },
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text(text = "API Test Menu")
            }
        }

        item {
            // 스플래시 화면으로 이동 버튼
            when (sessionStatus) {
                is SessionStatus.Authenticated -> {
                    Button(onClick = { navController.navigate(Matching) }) {
                        Text(text = "Go to Splash Screen")
                    }
                }

                is SessionStatus.Initializing,
                is SessionStatus.NotAuthenticated,
                is SessionStatus.RefreshFailure -> {
                    Button(onClick = { navController.navigate(Splash) }) {
                        Text(text = "Go to Splash Screen")
                    }
                }
            }
        }

        item {
            Button(onClick = { navController.navigate(HotDogMain) }) {
                Text(text = "Go to Splash Screen")
            }
        }

        item {
            Text(text = stringResource(id = R.string.menu_screen_apphelper_header))
        }

        item {
            Button(onClick = { navController.navigate(AppHelperNodes) }) {
                Text(text = stringResource(id = R.string.menu_screen_nodes_item))
            }
        }

        item {
            Button(onClick = { navController.navigate(AppHelperNodesListener) }) {
                Text(text = stringResource(id = R.string.menu_screen_nodes_listener_item))
            }
        }

        item {
            Text(
                text = stringResource(id = R.string.menu_screen_inapp_prompts_header),
                modifier = Modifier.padding(top = 10.dp),
            )
        }

        item {
            Button(onClick = { navController.navigate(InstallAppPromptDemo) }) {
                Text(text = stringResource(id = R.string.menu_screen_install_app_demo_item))
            }
        }

        item {
            Button(onClick = { navController.navigate(ReEngagePromptDemo) }) {
                Text(text = stringResource(id = R.string.menu_screen_reengage_demo_item))
            }
        }

        item {
            Button(onClick = { navController.navigate(SignInPromptDemo) }) {
                Text(text = stringResource(id = R.string.menu_screen_signin_demo_item))
            }
        }

        item {
            Button(onClick = { navController.navigate(InstallTilePromptDemo) }) {
                Text(text = stringResource(id = R.string.menu_screen_install_tile_demo_item))
            }
        }

        item {
            Button(onClick = { navController.navigate(InstallAppCustomPromptDemo) }) {
                Text(text = stringResource(id = R.string.menu_screen_install_app_custom_demo_item))
            }
        }

        item {
            Button(onClick = { navController.navigate(ReEngageCustomPromptDemo) }) {
                Text(text = stringResource(id = R.string.menu_screen_reengage_custom_demo_item))
            }
        }

        item {
            Button(onClick = { navController.navigate(SignInCustomPromptDemo) }) {
                Text(text = stringResource(id = R.string.menu_screen_signin_custom_demo_item))
            }
        }

        item {
            Button(onClick = { navController.navigate(InstallTileCustomPromptDemo) }) {
                Text(text = stringResource(id = R.string.menu_screen_install_tile_custom_demo_item))
            }
        }

        item {
            Text(
                text = stringResource(id = R.string.menu_screen_datalayer_header),
                modifier = Modifier.padding(top = 10.dp),
            )
        }

        item {
            Button(onClick = { navController.navigate(Counter) }) {
                Text(text = stringResource(id = R.string.menu_screen_counter_item))
            }
        }

        item {
            Button(onClick = { navController.navigate(HeartRate) }) {
                Text(text = stringResource(id = R.string.menu_screen_heart_rate))
            }
        }

        item {
            Button(onClick = { navController.navigate(EmojiTest) }) {
                Text(text = stringResource(id = R.string.menu_screen_emoji_test))
            }
        }

        item {
            Button(onClick = { navController.navigate(StepCount) }) {
                Text(text = stringResource(id = R.string.menu_screen_step_count))
            }
        }
    }
}
