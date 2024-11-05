package com.google.android.horologist.datalayer.sample.screens.hotdog.setting.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.horologist.datalayer.sample.R
import com.google.android.horologist.datalayer.sample.screens.UserManual
import com.google.android.horologist.datalayer.sample.ui.theme.bgBoxColor
import com.google.android.horologist.datalayer.sample.ui.theme.textColor

@Composable
fun SettingPage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 상단 타이틀 이미지
        Image(
            painter = painterResource(R.drawable.setting_title),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .padding(top = 32.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        // "사용 설명서" 버튼
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = bgBoxColor,
            shadowElevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clickable { navController.navigate(UserManual) }
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "사용 설명서",
                    fontSize = 18.sp,
                    color = textColor,
                    fontWeight = FontWeight.Bold
                )
                Image(
                    painter = painterResource(R.drawable.ic_arrow),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // "데이터 삭제" 버튼
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = bgBoxColor,
            shadowElevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clickable { navController.navigate(UserManual) }
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "데이터 삭제",
                    fontSize = 18.sp,
                    color = textColor,
                    fontWeight = FontWeight.Bold
                )
                Image(
                    painter = painterResource(R.drawable.ic_arrow),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // "로그아웃" 버튼
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = bgBoxColor,
            shadowElevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clickable { navController.navigate(UserManual) }
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "로그아웃",
                    fontSize = 18.sp,
                    color = textColor,
                    fontWeight = FontWeight.Bold
                )
                Image(
                    painter = painterResource(R.drawable.ic_arrow),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
