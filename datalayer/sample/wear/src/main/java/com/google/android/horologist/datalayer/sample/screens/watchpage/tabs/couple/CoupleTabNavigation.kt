package com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.couple

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration
import androidx.navigation.navigation
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.couple.screens.WalkScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.couple.screens.TimeTogetherScreen
import com.google.android.horologist.datalayer.sample.screens.watchpage.tabs.couple.screens.EmojiScreen

sealed class CoupleTabScreen(val route: String) {
    object Main : CoupleTabScreen("couple_main")
    object Walk : CoupleTabScreen("couple_walk")
    object TimeTogether : CoupleTabScreen("couple_time")
    object Emoji : CoupleTabScreen("couple_emoji")
}

fun sendEmojiToRecipient(emoji: String) {
    println("전송된 이모지: $emoji")
}

//fun sendEmojiToRecipient(emoji: String) {
//    // 데이터 전송을 위한 코드 - 예시로 Data Layer API 사용
//    val putDataMapRequest = PutDataMapRequest.create("/emoji_path")
//    putDataMapRequest.dataMap.putString("emoji", emoji)
//    val putDataRequest = putDataMapRequest.asPutDataRequest()
//
//    Wearable.getDataClient(context).putDataItem(putDataRequest)
//        .addOnSuccessListener {
//            Log.d("Emoji", "이모지가 성공적으로 전송되었습니다: $emoji")
//        }
//        .addOnFailureListener { e ->
//            Log.e("Emoji", "이모지 전송 실패", e)
//        }
//}

fun NavGraphBuilder.coupleTabNavigation(
    navController: NavHostController
) {
        composable(CoupleTabScreen.Main.route) {
            CoupleTab(
                onWalkClick = { navController.navigate(CoupleTabScreen.Walk.route) },
                onTimeTogetherClick = { navController.navigate(CoupleTabScreen.TimeTogether.route) },
                onEmojiClick = { navController.navigate(CoupleTabScreen.Emoji.route) }
            )
        }

        composable(CoupleTabScreen.Walk.route) {
            WalkScreen()
        }

        composable(CoupleTabScreen.TimeTogether.route) {
            val timeTogetherDuration = (3 * 60 + 30).toDuration(DurationUnit.MINUTES)
            TimeTogetherScreen(timeTogether = timeTogetherDuration)
        }

        composable(CoupleTabScreen.Emoji.route) {
            EmojiScreen(onEmojiSelected = { emoji ->
                sendEmojiToRecipient(emoji)
            })
        }
    }
