// watchpage/UserStatusOverlay.kt

package com.google.android.horologist.datalayer.sample.screens.watchpage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
//import androidx.activity.compose.BackHandler
//import androidx.navigation.NavController
import com.google.android.horologist.datalayer.sample.screens.watchpage.state.user.UserUiState

@Composable
fun UserStatusOverlay(
    userState: UserUiState,
    showTextOverlay: Boolean,
    onCloseOverlay: () -> Unit,
    onConfirmAction: () -> Unit,
//    navController: NavController
) {
    if (!userState.isCoupleMatched) {
        OverlayMessage("모바일에서 커플 매칭을 완료해주세요")
        return
    }

    if (userState.eating && showTextOverlay) {
        OverlayWithActions(
            message = "움직임을 감지했습니다.\n식사 중이십니까?",
            onCloseOverlay = onCloseOverlay,
            onConfirmAction = onConfirmAction
        )
    }

    if (userState.working && showTextOverlay) {
        OverlayWithActions(
            message = "움직임을 감지했습니다.\n근무 중이십니까?",
            onCloseOverlay = onCloseOverlay,
            onConfirmAction = onConfirmAction
        )
    }

    if (userState.commuting && showTextOverlay) {
        OverlayWithActions(
            message = "움직임을 감지했습니다.\n출근 중이십니까?",
            onCloseOverlay = onCloseOverlay,
            onConfirmAction = onConfirmAction
        )
    }

    if (userState.commutingRecipient && showTextOverlay) {
        OverlayWithActionsRecipient(
            message = "상대방이 출근 중 입니다.",
            onCloseOverlay = onCloseOverlay
        )
    }
    if (userState.workingRecipient && showTextOverlay) {
        OverlayWithActionsRecipient(
            message = "상대방이 근무 중 입니다.",
            onCloseOverlay = onCloseOverlay
        )
    }
    if (userState.eatingRecipient && showTextOverlay) {
        OverlayWithActionsRecipient(
            message = "상대방이 식사 중 입니다.",
            onCloseOverlay = onCloseOverlay
        )
    }
}

@Composable
private fun OverlayMessage(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun OverlayWithActions(
    message: String,
    onCloseOverlay: () -> Unit,
    onConfirmAction: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp), // 화면 가장자리에 여백 추가
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), // 콘텐츠 크기에 맞춰 높이 조절
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = message,
                color = Color.White,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(), // 전체 너비를 채워 줄바꿈을 허용
                maxLines = 3, // 필요한 경우 줄 수 조정
                softWrap = true // 자동 줄바꿈
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Button(onClick = onCloseOverlay) {
                    Text("닫기")
                }
                Button(onClick = onConfirmAction) {
                    Text("확인")
                }
            }
        }
    }
}

@Composable
private fun OverlayWithActionsRecipient(
    message: String,
    onCloseOverlay: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = message,
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Button(onClick = onCloseOverlay) {
                    Text("닫기")
                }
            }
        }
    }
}

