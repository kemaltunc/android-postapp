package com.tunc.wallpaperandroid.domain.usecase

import com.tunc.wallpaperandroid.data.network.entity.request.LoginRequest
import com.tunc.wallpaperandroid.data.network.entity.request.RegisterRequest
import com.tunc.wallpaperandroid.data.pref.PrefHelper
import com.tunc.wallpaperandroid.domain.entity.UserEntity
import com.tunc.wallpaperandroid.domain.mapper.toUser
import com.tunc.wallpaperandroid.domain.repository.AccountRepository
import javax.inject.Inject

class AccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val prefHelper: PrefHelper
) {

    suspend fun login(loginRequest: LoginRequest): UserEntity {

        val data = accountRepository.login(loginRequest)

        return data.toUser()
    }

    suspend fun register(registerRequest: RegisterRequest): UserEntity {
        val data = accountRepository.register(registerRequest)

        return data.toUser()
    }


    fun isLogined() = accountRepository.isLogined()


    fun logout() = prefHelper.clear()

}