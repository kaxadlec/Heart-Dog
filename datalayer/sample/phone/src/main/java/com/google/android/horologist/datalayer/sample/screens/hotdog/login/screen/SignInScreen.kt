package com.google.android.horologist.datalayer.sample.screens.hotdog.login.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.horologist.datalayer.sample.R
import com.google.android.horologist.datalayer.sample.screens.HotDogMain
import com.google.android.horologist.datalayer.sample.screens.Matching
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.manager.UserSessionManager
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.DogViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.SignInViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.UserViewModel
import io.github.jan.supabase.auth.status.SessionStatus
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    dogViewModel: DogViewModel = hiltViewModel(),
    signInViewModel: SignInViewModel = hiltViewModel(),
    userViewModel: UserViewModel,
) {

//    Scaffold(
//        topBar = {
//            TopAppBar(
//                navigationIcon = {
//                    IconButton(onClick = {
//                        navController.navigateUp()
//                    }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = null,
//                            tint = MaterialTheme.colorScheme.onPrimary
//                        )
//                    }
//                },
//                title = {
//                    Text(
//                        text = "Login",
//                        color = MaterialTheme.colorScheme.onPrimary,
//                    )
//                },
//            )
//        }
//    ) { paddingValues ->
//        Column(
//            modifier = modifier
//                .padding(paddingValues)
//                .padding(20.dp)
//        ) {
//            Button(modifier = modifier
//                .fillMaxWidth()
//                .padding(top = 12.dp),
//                onClick = {
//                    viewModel.onKakaoSignIn()
//                }) {
//                Text("Sign in with Kakao")
//            }
//        }
//    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.main_logo),
            contentDescription = null,
            modifier = Modifier
                .width(350.dp)
                .height(350.dp)
                .offset(y = (-50).dp),
            contentScale = ContentScale.Fit
        )

//        Image(
//            painter = painterResource(id = R.drawable.login_button),
//            contentDescription = null,
//            modifier = Modifier
//                .width(370.dp)
//                .height(120.dp)
//                .offset(y = 170.dp)
//                .clickable(
//                    indication = null, // 클릭 피드백 제거
//                    interactionSource = remember { MutableInteractionSource() } // InteractionSource 설정
//                ) {
////                    navController.navigate(Matching)
//                    signInViewModel.onGoogleSignIn()
////                    updateFcmToken(signInViewModel, notificationViewModel)
//                },
//            contentScale = ContentScale.Fit
//        )

        Image(
            painter = painterResource(id = R.drawable.login_button),
            contentDescription = null,
            modifier = Modifier
                .width(370.dp)
                .height(120.dp)
                .offset(y = 170.dp)
                .clickable {
                    // 로그인 버튼 클릭시
                    if (signInViewModel.sessionStatus.value is SessionStatus.Authenticated) {
                        // 이미 로그인 되어 있으면
                        val currentUser = signInViewModel.currentUser.value
                        currentUser?.userId?.let { userId ->
                            userViewModel.setUserId(userId)
                            signInViewModel.saveUserSession(userId)
                            dogViewModel.saveDogSession(userId)

                            if (currentUser.matching) {
                                navController.navigate(HotDogMain)
                            } else {
                                navController.navigate(Matching)
                            }
                        }
                    } else {
                        // 로그인 안되어 있으면 로그인 진행
                        signInViewModel.onGoogleSignIn()
                    }
                }
        )

        Button(modifier = Modifier
            .width(370.dp)
            .offset(y = 240.dp)
            .padding(top = 12.dp),
            onClick = {
                signInViewModel.onKakaoSignIn()
            }) {
                Text("Sign in with Kakao")
            }
    }
}
