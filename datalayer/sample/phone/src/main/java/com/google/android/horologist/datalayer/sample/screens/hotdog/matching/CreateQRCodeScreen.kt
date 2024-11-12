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
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.util.*
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.google.android.horologist.datalayer.sample.R
import com.google.android.horologist.datalayer.sample.repository.UserRepository
import com.google.android.horologist.datalayer.sample.screens.HotDogMain
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.UserViewModel

@Composable
fun CreateQRCodeScreen(navController: NavHostController, userViewModel: UserViewModel) {
    val userId = userViewModel.userId.collectAsState().value ?: return

    val randomCode = remember { generateRandomCode() }
    var qrBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var remainingTime by remember { mutableStateOf(120) } // 타이머 (2분)
    val minutes = (remainingTime / 60).toString().padStart(2, '0')
    val seconds = (remainingTime % 60).toString().padStart(2, '0')

    // 커스텀 글꼴 로드 (res/font/jejudoldam 파일 위치 필요)
    val customFont = FontFamily(Font(R.font.jejudoldam, FontWeight.Normal))

    LaunchedEffect(randomCode) {
        qrBitmap = generateQRCode(randomCode)
        val userId = 1L

        // 코드 업데이트
        userViewModel.updateUserCode(userId, randomCode)

        // 타이머 실행 & 매칭 상태 체크
        while (remainingTime > 0) {
            delay(1000L)

            // UserRepository를 통해 매칭 상태 체크
            if (userViewModel.checkUserMatching(userId)) {
                // 매칭되었다면 메인화면으로 이동
                navController.navigate(HotDogMain) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                }
                return@LaunchedEffect
            }

            remainingTime -= 1
        }

        // 시간 초과시
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
            // QR 코드 표시
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

            // 남은 시간 텍스트 표시
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

// 6자리의 랜덤 코드 생성 함수
fun generateRandomCode(): String {
    val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    return (1..6)
        .map { chars.random() }
        .joinToString("")
}

// QR 코드 생성 함수
suspend fun generateQRCode(text: String): Bitmap? = withContext(Dispatchers.IO) {
    try {
        val size = 250 // QR 코드 크기 설정
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
