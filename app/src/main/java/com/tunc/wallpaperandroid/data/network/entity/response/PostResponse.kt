package com.tunc.wallpaperandroid.data.network.entity.response


import com.squareup.moshi.Json

data class PostResponse(
    @field:Json(name = "description")
    var description: String?,
    @field:Json(name = "deviceName")
    var deviceName: String?,
    @field:Json(name = "path")
    var path: String?,
    @field:Json(name = "value")
    var value: String?,
    @field:Json(name = "owner")
    var owner: UserResponse?,
    @field:Json(name = "likeCount")
    var likeCount: Int?,
    @field:Json(name = "commentCount")
    var commentCount: Int?,
    @field:Json(name = "isLike")
    var isLike: Boolean?
)

