package com.google.android.horologist.datalayer.sample.screens.hotdog.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.manager.UserSessionManager
import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.DogRepository
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.UserViewModel

class DogViewModelFactory(
    private val dogRepository: DogRepository,
    private val userSessionManager: UserSessionManager
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DogViewModel::class.java)) {
            return DogViewModel(dogRepository, userSessionManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
