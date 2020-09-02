package com.tunc.wallpaperandroid.data.network.service

import com.tunc.wallpaperandroid.data.network.entity.response.UploadResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadService {
    @Multipart
    @POST("Upload")
    suspend fun uploadMedia(
        @Part file: MultipartBody.Part
    ): UploadResponse
}