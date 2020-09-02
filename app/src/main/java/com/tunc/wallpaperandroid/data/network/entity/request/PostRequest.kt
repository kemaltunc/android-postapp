package com.tunc.wallpaperandroid.data.network.entity.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class PostRequest(
    var description: String?,
    var deviceName: String?,
    var path: String?
)

