package com.tunc.wallpaperandroid.domain.usecase

import com.tunc.wallpaperandroid.data.network.entity.request.PostIDRequest
import com.tunc.wallpaperandroid.data.network.entity.request.PostRequest
import com.tunc.wallpaperandroid.domain.entity.PostEntity
import com.tunc.wallpaperandroid.domain.mapper.toPost
import com.tunc.wallpaperandroid.domain.repository.PostRepository
import javax.inject.Inject

class PostUseCase @Inject constructor(private val postRepository: PostRepository) {

    suspend fun createPost(postRequest: PostRequest): Boolean {
        val data = postRepository.createPost(postRequest)
        return data.result ?: false
    }

    suspend fun getAll(page: Int): List<PostEntity> {
        return postRepository.getAll(page).map {
            it.toPost()
        }
    }

    suspend fun like(item: PostEntity): Boolean {
        val postIDRequest = PostIDRequest(item.postId)
        return if (item.liked) {
            postRepository.like(postIDRequest).result ?: false
        } else {
            postRepository.unLike(postIDRequest).result ?: false
        }
    }

}