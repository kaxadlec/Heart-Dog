package com.google.android.horologist.datalayer.sample.screens.hotdog.login.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import com.google.android.horologist.datalayer.sample.R
import com.google.android.horologist.datalayer.sample.screens.HotDogMain
import com.google.android.horologist.datalayer.sample.screens.Matching
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.SignInViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.UserViewModel
import io.github.jan.supabase.auth.status.SessionStatus
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.LocalDogViewModel

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    signInViewModel: SignInViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
) {
    val dogViewModel = LocalDogViewModel.current
    var dogDetails = dogViewModel.dogDetails.collectAsState().value
    val context = LocalContext.current

    // 커스텀 ImageLoader 생성하여 GIF 애니메이션 지원
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(GifDecoder.Factory())
        }
        .build()

    LaunchedEffect(dogDetails) {
        dogDetails?.let {
            if (signInViewModel.currentUser.value?.matching == true) {
                navController.navigate(HotDogMain)
            } else {
                navController.navigate(Matching)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        contentAlignment = Alignment.Center
    ){
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(R.drawable.move_logo) // main_logo.gif 리소스
                .size(Size.ORIGINAL) // 원래 크기 유지
                .build(),
            contentDescription = null,
            imageLoader = imageLoader,
            modifier = Modifier
                .width(400.dp)
                .height(400.dp)
                .offset(y = (-50).dp),
            contentScale = ContentScale.Fit
        )


        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .offset(y = 170.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Google 로그인 버튼
            Surface(
                onClick = {
                    if (signInViewModel.sessionStatus.value is SessionStatus.Authenticated) {
                        val currentUser = signInViewModel.currentUser.value
                        currentUser?.userId?.let { userId ->
                            userViewModel.setUserId(userId)
                            signInViewModel.saveUserSession(userId)
                            dogViewModel.initUserAndSaveDogSession(userId, userViewModel)

                            if (currentUser.matching) {
                                navController.navigate(HotDogMain)
                            } else {
                                navController.navigate(Matching)
                            }
                        }
                    } else {
                        signInViewModel.onGoogleSignIn()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(4.dp),
                color = Color.White,
                border = BorderStroke(1.dp, Color(0xFFDDDDDD)),
                shadowElevation = 2.dp,
                tonalElevation = 0.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_google_logo),
                        contentDescription = "Google logo",
                        modifier = Modifier.size(20.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Google 계정으로 로그인",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color(0xFF333333)
                    )
                }
            }

            // Kakao 로그인 버튼
            Surface(
                onClick = {
                    signInViewModel.onKakaoSignIn()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(4.dp),
                color = Color(0xFFFEE500),
                shadowElevation = 2.dp,
                tonalElevation = 0.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_kakao_logo),
                        contentDescription = "Kakao logo",
                        modifier = Modifier.size(20.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "KAKAO 계정으로 로그인",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color(0xFF391B1B)
                    )
                }
            }
        }
    }
}