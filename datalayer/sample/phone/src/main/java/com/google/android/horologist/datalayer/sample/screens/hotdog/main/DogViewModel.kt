package com.google.android.horologist.datalayer.sample.screens.hotdog.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.DogRepository
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.Dog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DogViewModel : ViewModel() {
    private val dogRepository = DogRepository()
    private val _dogInfo = MutableStateFlow<Dog?>(null)
    val dogInfo: StateFlow<Dog?> = _dogInfo

    fun fetchDogById(dogId: Long) {
        viewModelScope.launch {
            val dog = dogRepository.getDogById(dogId)
            _dogInfo.value = dog
        }
    }
}
