package com.google.android.horologist.datalayer.sample.screens.hotdog.login.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.horologist.datalayer.sample.screens.hotdog.data.models.User
import com.google.android.horologist.datalayer.sample.screens.hotdog.login.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.status.SessionSource
import io.github.jan.supabase.auth.status.SessionStatus
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.filter.FilterOperator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val supabaseClient: SupabaseClient,
    private val authenticationRepository: AuthenticationRepository,
) : ViewModel() {

    // 세션 상태를 나타내는 StateFlow
    private val _sessionStatus = MutableStateFlow<SessionStatus>(SessionStatus.Initializing)
    val sessionStatus: StateFlow<SessionStatus> = _sessionStatus

    // 사용자 정보 관리하는 StateFlow
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    init {
        viewModelScope.launch {
            // sessionStatus 수집 시작
            supabaseClient.auth.sessionStatus.collect { status ->
                _sessionStatus.value = status // 상태 업데이트

                when (status) {
                    is SessionStatus.Authenticated -> {
                        retrieveUserForAuthId()
                        Log.d("Received new authenticated session.", "${currentUser.value?.userId}")
                        when (status.source) {
                            SessionSource.External -> println("External login")
                            is SessionSource.Refresh -> println("Session refreshed")
                            is SessionSource.SignIn -> println("Signed in")
                            is SessionSource.SignUp -> println("Signed up")
                            SessionSource.Storage -> println("Loaded from storage")
                            SessionSource.Unknown -> println("Unknown source")
                            is SessionSource.UserChanged -> println("User data changed")
                            is SessionSource.UserIdentitiesChanged -> println("User identities changed")
                            SessionSource.AnonymousSignIn -> TODO()
                        }
                    }
                    SessionStatus.Initializing -> println("Initializing")
                    is SessionStatus.RefreshFailure -> println("Refresh failure: ${status.cause}")
                    is SessionStatus.NotAuthenticated -> {
                        if (status.isSignOut) {
                            println("User signed out")
                        } else {
                            println("User not signed in")
                        }
                    }
                }
            }
        }
    }

    // 현재 세션의 사용자 정보 가져오기
    private fun retrieveUserForAuthId() {
        viewModelScope.launch {
            try {
                val supabaseUser = supabaseClient.auth.retrieveUserForCurrentSession(updateSession = true)
                Log.d("supabaseUser", "$supabaseUser")
                val authId = supabaseUser.id
                Log.d("user auth id", authId)

                val userRecord = supabaseClient
                    .from("users")
                    .select {
                        filter {
                            filter(column = "users_id_fkey", operator = FilterOperator.EQ, value = authId)
                        }
                    }
                    .decodeSingle<User>()
                // Decode as a User object
                _currentUser.value = userRecord

                Log.d("User currentUser", "${currentUser.value?.userId}")
            } catch (e: Exception) {
                println("Failed to retrieve user: ${e.message}")
            }
        }
    }

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