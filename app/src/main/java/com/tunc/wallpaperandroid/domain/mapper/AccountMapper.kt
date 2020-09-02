package com.tunc.wallpaperandroid.domain.mapper

import com.tunc.wallpaperandroid.data.network.entity.response.UserResponse
import com.tunc.wallpaperandroid.domain.entity.UserEntity

fun UserResponse.toUser() = UserEntity(
    name = this.name ?: "",
    surname = this.surname ?: "",
    imagePath = this.path ?: "",
    fullName = "${this.name ?: ""} ${this.surname ?: ""}"
)