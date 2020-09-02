package com.tunc.wallpaperandroid.presentation.adapter

import com.tunc.wallpaperandroid.domain.entity.PostEntity

interface PostInterface {
    fun like(item: PostEntity)
    fun comment(item: PostEntity)
}