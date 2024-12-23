package com.example.ptb.service

import com.example.ptb.models.UserResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {

    @GET("user")
    suspend fun getUser(
        @Header("Authorization") token: String
    ) : UserResponse

}