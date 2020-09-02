package com.tunc.wallpaperandroid.data.network.entity.response

import com.squareup.moshi.Json

data class UserResponse(
    @field:Json(name = "email")
    var email: String?,
    @field:Json(name = "password")
    var password: String?,
    @field:Json(name = "name")
    var name: String?,
    @field:Json(name = "path")
    var path: String?,
    @field:Json(name = "surname")
    var surname: String?,
    @field:Json(name = "accessToken")
    var accessToken: String?,
    @field:Json(name = "refreshToken")
    var refreshToken: String?
)