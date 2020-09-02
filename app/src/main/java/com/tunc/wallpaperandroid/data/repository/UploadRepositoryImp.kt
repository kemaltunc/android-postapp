package com.tunc.wallpaperandroid.data.repository

import com.tunc.wallpaperandroid.data.network.entity.response.UploadResponse
import com.tunc.wallpaperandroid.data.network.service.UploadService
import com.tunc.wallpaperandroid.domain.repository.UploadRepository
import okhttp3.MultipartBody

class UploadRepositoryImp constructor(
    private val uploadService: UploadService
) : UploadRepository {

    override suspend fun uplaodFile(file: MultipartBody.Part): UploadResponse {
        return uploadService.uploadMedia(file)
    }
}