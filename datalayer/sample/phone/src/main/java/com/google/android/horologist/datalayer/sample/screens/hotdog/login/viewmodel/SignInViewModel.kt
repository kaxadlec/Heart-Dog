package com.google.android.horologist.datalayer.sample.screens.hotdog.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.horologist.datalayer.sample.screens.hotdog.login.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

//    private val _email = MutableStateFlow("")
//    val email: Flow<String> = _email
//
//    private val _password = MutableStateFlow("")
//    val password = _password
//
//    fun onEmailChange(email: String) {
//        _email.value = email
//    }
//
//    fun onPasswordChange(password: String) {
//        _password.value = password
//    }

//    fun onSignIn() {
//        viewModelScope.launch {
//            authenticationRepository.signIn(
//                email = _email.value,
//                password = _password.value
//            )
//        }
//    }

    fun onKakaoSignIn() {
        viewModelScope.launch {
            authenticationRepository.signInWithKakao()
        }
    }

    fun onGoogleSignIn() {
        viewModelScope.launch {
            authenticationRepository.signInWithGoogle()
        }
    }

}