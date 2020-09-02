package com.tunc.wallpaperandroid.data.network.entity.request

data class RegisterRequest(
    var email: String,
    var password: String,
    var name: String,
    var surname: String,
    var path: String
)