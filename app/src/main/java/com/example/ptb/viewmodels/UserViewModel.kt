package com.example.ptb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ptb.data.TokenDataStore
import com.example.ptb.models.UserData
import com.example.ptb.service.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val tokenDataStore: TokenDataStore,
    private val userService: UserService
) : ViewModel() {

    private val _user = MutableStateFlow<UserData?>(null)
    val user: StateFlow<UserData?> = _user

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            val token = tokenDataStore.accessToken.firstOrNull()
            if (!token.isNullOrEmpty()) {
                try {
                    val response = userService.getUser("Bearer $token")
                    _user.value = response.data
                } catch (e: Exception) {
                    e.printStackTrace()
                    _errorMessage.value = "Gagal mengambil data pengguna"
                }
            } else {
                _errorMessage.value = "Token tidak ditemukan"
            }
        }
    }

}