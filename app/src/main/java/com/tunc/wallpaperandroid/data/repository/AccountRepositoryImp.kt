package com.tunc.wallpaperandroid.data.repository

import com.tunc.wallpaperandroid.data.network.entity.request.LoginRequest
import com.tunc.wallpaperandroid.data.network.entity.request.RegisterRequest
import com.tunc.wallpaperandroid.data.network.entity.response.UserResponse
import com.tunc.wallpaperandroid.data.network.service.AccountService
import com.tunc.wallpaperandroid.data.pref.PrefHelper
import com.tunc.wallpaperandroid.domain.repository.AccountRepository

class AccountRepositoryImp constructor(
    private val prefHelper: PrefHelper,
    private val accountService: AccountService
) : AccountRepository {
    override fun isLogined(): Boolean {
        val token = prefHelper.getAuthorizationToken()
        return !token.isNullOrEmpty()
    }

    override suspend fun login(loginRequest: LoginRequest): UserResponse {

        val data = accountService.login(loginRequest)
        prefHelper.saveAuthorizationToken(data.accessToken)

        return data
    }

    override suspend fun register(request: RegisterRequest): UserResponse {

        val data = accountService.register(request)
        prefHelper.saveAuthorizationToken(data.accessToken)

        return data
    }

}