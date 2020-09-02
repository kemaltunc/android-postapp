package com.tunc.wallpaperandroid.data.repository

import com.tunc.wallpaperandroid.data.network.entity.request.PostIDRequest
import com.tunc.wallpaperandroid.data.network.entity.request.PostRequest
import com.tunc.wallpaperandroid.data.network.entity.response.PostResponse
import com.tunc.wallpaperandroid.data.network.entity.response.ResultResponse
import com.tunc.wallpaperandroid.data.network.service.PostService
import com.tunc.wallpaperandroid.data.pref.PrefHelper
import com.tunc.wallpaperandroid.domain.repository.PostRepository


class PostRepositoryImp constructor(
    private val prefHelper: PrefHelper,
    private val postService: PostService
) : PostRepository {


    override suspend fun createPost(postRequest: PostRequest): ResultResponse {
        return postService.createPost(postRequest)
    }

    override suspend fun getAll(page: Int): List<PostResponse> {
        return postService.getAll(page)
    }

    override suspend fun like(postIDRequest: PostIDRequest): ResultResponse {
        return postService.like(postIDRequest)
    }

    override suspend fun unLike(postIDRequest: PostIDRequest): ResultResponse {
        return postService.unLike(postIDRequest)
    }

}