package com.tunc.wallpaperandroid.domain.usecase

import com.tunc.wallpaperandroid.domain.repository.UploadRepository
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

class UploadUseCase @Inject constructor(private val uploadRepository: UploadRepository) {

    suspend fun uploadFile(file: File): String {

        val requestFile = RequestBody.create(MediaType.parse("image*/"), file)
        val body = MultipartBody.Part.createFormData("files", file.name, requestFile)
        return uploadRepository.uplaodFile(body).path ?: ""
    }
}