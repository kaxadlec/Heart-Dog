package com.google.android.horologist.datalayer.sample.screens.hotdog.repository

import com.google.android.horologist.datalayer.sample.screens.hotdog.data.SupabaseClientProvider
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.Dog
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.Exp
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogRepository {
    // 특정 dog_id와 일치하는 개 정보를 가져오는 함수
    suspend fun getDogById(dogId: Long): Dog? = withContext(Dispatchers.IO) {
        try {
            SupabaseClientProvider.supabase
                .from("dog")
                .select()
                .decodeList<Dog>()
                .find { it.dogId == dogId } // dogId와 일치하는 첫 번째 개 정보를 반환
        } catch (e: Exception) {
            null
        }
    }

    // 특정 dog의 레벨에 따라 최대 경험치 가져오는 함수
    suspend fun getMaxExpByLevel(level: Int): Exp? = withContext(Dispatchers.IO) {
        try {
            SupabaseClientProvider.supabase
                .from("exp")
                .select()
                .decodeList<Exp>()
                .find { it.level == level } // level과 일치하는 maxExp 정보를 반환
        } catch (e: Exception) {
            null
        }
    }
}
