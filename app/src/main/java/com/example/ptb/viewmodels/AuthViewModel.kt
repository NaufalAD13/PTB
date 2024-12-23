package com.example.ptb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ptb.data.TokenDataStore
import com.example.ptb.models.LoginRequest
import com.example.ptb.service.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val tokenDataStore: TokenDataStore,
    private val authService: AuthService
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Initial)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    fun login(email: String, password: String) = viewModelScope.launch {
        try {
            _authState.value = AuthState.Loading

            tokenDataStore.clearToken()

            val loginResponse = authService.login(
                LoginRequest(email, password)
            )
            tokenDataStore.saveToken(loginResponse.token)
            _authState.value = AuthState.LoginSuccess
        } catch (e: Exception) {
            _authState.value = AuthState.Error(e.message ?: "Login gagal")
        }
    }

    fun logout() = viewModelScope.launch {
        tokenDataStore.clearToken()
        _authState.value = AuthState.Logout
    }
}

sealed class AuthState {
    object Initial : AuthState()
    object Loading : AuthState()
    object LoginSuccess : AuthState()
    data class Error(val message: String) : AuthState()
    object Logout : AuthState()
}