package com.tunc.wallpaperandroid.data.network.service

import com.tunc.wallpaperandroid.data.network.entity.request.LoginRequest
import com.tunc.wallpaperandroid.data.network.entity.request.RegisterRequest
import com.tunc.wallpaperandroid.data.network.entity.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountService {
    @POST("Account/login")
    suspend fun login(@Body loginRequest: LoginRequest): UserResponse

    @POST("Account/register")
    suspend fun register(@Body registerRequest: RegisterRequest): UserResponse
}