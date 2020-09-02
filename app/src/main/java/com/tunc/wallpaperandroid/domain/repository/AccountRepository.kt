package com.tunc.wallpaperandroid.domain.repository

import com.tunc.wallpaperandroid.data.network.entity.request.LoginRequest
import com.tunc.wallpaperandroid.data.network.entity.request.RegisterRequest
import com.tunc.wallpaperandroid.data.network.entity.response.UserResponse

interface AccountRepository {
    suspend fun login(loginRequest: LoginRequest): UserResponse
    suspend fun register(request: RegisterRequest): UserResponse


    fun isLogined(): Boolean
}