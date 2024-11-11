package com.google.android.horologist.datalayer.sample.screens.hotdog.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.horologist.datalayer.sample.screens.hotdog.repository.DogRepository
import com.google.android.horologist.datalayer.sample.screens.hotdog.vm.UserViewModel

class DogViewModelFactory(
    private val userViewModel: UserViewModel,
    private val dogRepository: DogRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DogViewModel::class.java)) {
            return DogViewModel(userViewModel, dogRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
