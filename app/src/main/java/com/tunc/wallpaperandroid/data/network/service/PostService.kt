package com.tunc.wallpaperandroid.data.network.service

import com.tunc.wallpaperandroid.data.network.entity.request.PostIDRequest
import com.tunc.wallpaperandroid.data.network.entity.request.PostRequest
import com.tunc.wallpaperandroid.data.network.entity.response.PostResponse
import com.tunc.wallpaperandroid.data.network.entity.response.ResultResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostService {
    @POST("Post/create")
    suspend fun createPost(@Body postRequest: PostRequest): ResultResponse

    @GET("Post/getAll/{page}")
    suspend fun getAll(@Path("page") page: Int): List<PostResponse>

    @POST("Post/like")
    suspend fun like(@Body postIDRequest: PostIDRequest): ResultResponse

    @POST("Post/unLike")
    suspend fun unLike(@Body postIDRequest: PostIDRequest): ResultResponse
}