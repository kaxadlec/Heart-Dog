package com.google.android.horologist.datalayer.sample.screens.hotdog.matching

import android.graphics.Bitmap
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.android.horologist.datalayer.sample.screens.hotdog.common.LogoHeader
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.google.android.horologist.datalayer.sample.R
import com.google.android.horologist.datalayer.sample.screens.HotDogMain
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.DogViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.SignInViewModel
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.UserViewModel

@Composable
fun CreateQRCodeScreen(navController: NavHostController, userViewModel: UserViewModel, dogViewModel: DogViewModel) {
    val signInViewModel: SignInViewModel = hiltViewModel()
    val currentUser by signInViewModel.currentUser.collectAsState()

    val randomCode = remember { generateRandomCode() }
    var qrBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var remainingTime by remember { mutableStateOf(120) } // 타이머 (2분)
    val minutes = (remainingTime / 60).toString().padStart(2, '0')
    val seconds = (remainingTime % 60).toString().padStart(2, '0')

    val customFont = FontFamily(Font(R.font.jejudoldam, FontWeight.Normal))

    if (currentUser == null) {
        // 로딩 UI
        LoadingScreen()
    } else {
        LaunchedEffect(randomCode) {
            qrBitmap = generateQRCode(randomCode)
            userViewModel.updateUserCode(currentUser?.userId!!, randomCode)

            while (remainingTime > 0) {
                delay(1000L)
                if (userViewModel.checkUserMatching(currentUser?.userId!!)) {

                    // 매칭 성공시 강아지 정보 가져오기
                    dogViewModel.fetchDogIdAndDetails(currentUser?.userId!!)

                    navController.navigate(HotDogMain) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                    return@LaunchedEffect
                }
                remainingTime -= 1
            }
            navController.popBackStack()
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            LogoHeader(navController = navController)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                qrBitmap?.let { bitmap ->
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "Generated QR Code",
                        modifier = Modifier
                            .width(250.dp)
                            .height(250.dp)
                            .background(if (remainingTime <= 10) Color.Red else Color.White),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "$minutes:$seconds",
                    fontSize = 32.sp,
                    fontFamily = customFont,
                    color = if (remainingTime <= 10) Color.Red else Color(0xFFD66F24),
                    modifier = Modifier.padding(top = 1.dp)
                )
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

fun generateRandomCode(): String {
    val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    return (1..6)
        .map { chars.random() }
        .joinToString("")
}

fun generateQRCode(text: String): Bitmap? {
    return try {
        val size = 250
        val qrCodeWriter = QRCodeWriter()
        val bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, size, size)
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565)
        for (x in 0 until size) {
            for (y in 0 until size) {
                bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.Black.toArgb() else Color.White.toArgb())
            }
        }
        bitmap
    } catch (e: WriterException) {
        e.printStackTrace()
        null
    }
}
