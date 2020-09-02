package com.tunc.wallpaperandroid.data.network.entity.response


import com.squareup.moshi.Json

data class ResultResponse(
    @field:Json(name = "result")
    var result: Boolean?
)