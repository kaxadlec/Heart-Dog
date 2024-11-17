package com.google.android.horologist.datalayer.sample.screens.hotdog.setting.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.horologist.datalayer.sample.screens.hotdog.common.ButtonFooter
import com.google.android.horologist.datalayer.sample.ui.theme.textColor

@Composable
fun UserManualPage(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFE5B4))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp, bottom = 96.dp)  // 상단과 ButtonFooter를 위한 여백
                .verticalScroll(rememberScrollState()),  // 전체 Column을 스크롤 가능하게
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "사용 설명서",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 16.dp),
                color = textColor,
                fontWeight = FontWeight.Bold
            )

            ManualContent()
        }

        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            ButtonFooter(navController = navController)
        }
    }
}

@Composable
private fun ManualContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)  // 섹션 간격 조정
    ) {
        // 모바일 앱 섹션
        Text(
            text = "[모바일 앱 사용 설명]",
            fontSize = 18.sp,
            color = textColor,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // 모바일 앱 내용
        ManualSection(
            title = "1. 앱 설치 및 시작",
            content = listOf(
                "Play Store에서 앱을 설치한 다음 워치에도 설치해주세요.",
                "구글 계정으로 간편하게 로그인하실 수 있습니다."
            )
        )

        ManualSection(
            title = "2. 커플 연결하기",
            content = listOf(
                "회원가입 후 QR코드 발급 또는 QR코드 입력으로 커플을 연결합니다."
            )
        )

        ManualSection(
            title = "3. 메인 화면",
            content = listOf(
                "강아지의 프로필을 확인할 수 있습니다.",
                "이름, 상태, 경험치, 포만도를 확인할 수 있습니다."
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 워치 앱 섹션
        Text(
            text = "[워치 앱 사용 설명]",
            fontSize = 18.sp,
            color = textColor,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        ManualSection(
            title = "1. 메인 화면",
            content = listOf(
                "강아지의 상태를 확인할 수 있습니다.",
                "경험치와 포만도가 표시됩니다.",
                "포만도에 따라 강아지의 상태가 변화합니다."
            )
        )

        ManualSection(
            title = "2. 강아지 돌보기 (왼쪽 슬라이드)",
            content = listOf(
                "강아지에게 먹이를 줄 수 있습니다.",
                "보유한 먹이 개수를 확인할 수 있습니다.",
                "강아지를 호출할 수 있습니다."
            )
        )

        ManualSection(
            title = "3. 커플 상호작용 (오른쪽 슬라이드)",
            content = listOf(
                "함께 걸은 걸음 수를 확인할 수 있습니다.",
                "함께 보낸 시간을 확인할 수 있습니다.",
                "이모지 전송이 가능합니다."
            )
        )

        ManualSection(
            title = "4. 미니게임",
            content = listOf(
                "혼자 즐기는 순발력 게임",
                "커플과 함께하는 심박수 게임"
            )
        )

        ManualSection(
            title = "5. 먹이 시스템",
            content = listOf(
                "포만도는 최대 100까지 채울 수 있습니다.",
                "먹이 1개당 포만도 5가 증가합니다.",
                "하루 최대 10번 먹이를 줄 수 있습니다.",
                "포만도는 30분에 1씩 감소합니다."
            )
        )

        ManualSection(
            title = "6. 경험치 시스템",
            content = listOf(
                "포만도에 따라 경험치 획득이 달라집니다.",
                "먹이 1개당 기본 1 경험치가 주어집니다.",
                "포만도에 따라 0.5배~2배의 보너스가 적용됩니다."
            )
        )

        ManualSection(
            title = "7. 레벨 시스템",
            content = listOf(
                "1~10레벨: 100씩 증가 (100~1000)",
                "11~20레벨: 200씩 증가 (1200~3000)",
                "21~30레벨: 300씩 증가 (3300~6000)"
            )
        )
    }
}

@Composable
private fun ManualSection(title: String, content: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)  // 섹션 간격 조정
    ) {
        Text(
            text = title,
            fontSize = 14.sp,  // 글자 크기 조정
            color = textColor,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 2.dp)
        )

        content.forEach { text ->
            Text(
                text = "• $text",
                fontSize = 12.sp,  // 글자 크기 조정
                color = textColor,
                modifier = Modifier.padding(start = 8.dp, bottom = 2.dp)
            )
        }
    }
}