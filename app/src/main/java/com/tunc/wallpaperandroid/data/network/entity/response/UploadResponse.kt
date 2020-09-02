package com.tunc.wallpaperandroid.data.network.entity.response

import com.squareup.moshi.Json

data class UploadResponse(
    @field:Json(name = "path")
    var path: String?
)