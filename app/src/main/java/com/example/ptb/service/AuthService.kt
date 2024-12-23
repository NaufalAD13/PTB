package com.example.ptb.service

import com.example.ptb.models.LoginRequest
import com.example.ptb.models.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
}