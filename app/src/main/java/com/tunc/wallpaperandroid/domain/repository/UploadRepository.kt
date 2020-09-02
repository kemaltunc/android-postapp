package com.tunc.wallpaperandroid.domain.repository

import com.tunc.wallpaperandroid.data.network.entity.response.UploadResponse
import okhttp3.MultipartBody

interface UploadRepository {
    suspend fun uplaodFile(file: MultipartBody.Part): UploadResponse
}