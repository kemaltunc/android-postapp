package com.tunc.wallpaperandroid.domain.mapper

import com.tunc.wallpaperandroid.data.network.entity.response.PostResponse
import com.tunc.wallpaperandroid.domain.entity.PostEntity

fun PostResponse.toPost() = PostEntity(
    postId = this.value,
    sender = this.owner?.toUser(),
    deviceName = this.deviceName,
    description = this.description,
    imagePath = this.path,
    _likeCount = this.likeCount ?: 0,
    _commentCount = this.commentCount ?: 0,
    _isLiked = this.isLike ?: false
)