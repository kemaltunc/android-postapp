package com.tunc.wallpaperandroid.data.network.entity.request

import com.google.gson.annotations.SerializedName


data class LoginRequest(
    var email: String,
    var password: String
)