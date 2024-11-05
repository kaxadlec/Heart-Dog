package com.google.android.horologist.datalayer.sample.screens.hotdog.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.Exp
import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.DogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExpViewModel : ViewModel() {
    private val dogRepository = DogRepository()
    private val _expInfo = MutableStateFlow<Exp?>(null)
    val expInfo: StateFlow<Exp?> = _expInfo

    // 특정 level에 해당하는 maxExp 정보를 가져오는 함수
    fun fetchMaxExpByLevel(level: Int) {
        viewModelScope.launch {
            val exp = dogRepository.getMaxExpByLevel(level)
            _expInfo.value = exp
        }
    }
}
