package com.tunc.wallpaperandroid.domain.repository

import com.tunc.wallpaperandroid.data.network.entity.request.PostIDRequest
import com.tunc.wallpaperandroid.data.network.entity.request.PostRequest
import com.tunc.wallpaperandroid.data.network.entity.response.PostResponse
import com.tunc.wallpaperandroid.data.network.entity.response.ResultResponse

interface PostRepository {
    suspend fun createPost(postRequest: PostRequest): ResultResponse
    suspend fun getAll(page: Int): List<PostResponse>
    suspend fun like(postIDRequest: PostIDRequest): ResultResponse
    suspend fun unLike(postIDRequest: PostIDRequest): ResultResponse
}