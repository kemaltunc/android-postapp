package com.tunc.wallpaperandroid.di

import com.tunc.wallpaperandroid.data.network.service.AccountService
import com.tunc.wallpaperandroid.data.network.service.PostService
import com.tunc.wallpaperandroid.data.network.service.UploadService
import com.tunc.wallpaperandroid.data.pref.PrefHelper
import com.tunc.wallpaperandroid.data.repository.AccountRepositoryImp
import com.tunc.wallpaperandroid.data.repository.PostRepositoryImp
import com.tunc.wallpaperandroid.data.repository.UploadRepositoryImp
import com.tunc.wallpaperandroid.domain.repository.AccountRepository
import com.tunc.wallpaperandroid.domain.repository.PostRepository
import com.tunc.wallpaperandroid.domain.repository.UploadRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideAccountRepository(
        prefHelper: PrefHelper,
        accountService: AccountService
    ): AccountRepository {
        return AccountRepositoryImp(prefHelper, accountService)
    }

    @Singleton
    @Provides
    fun provideUploadRepository(
        uploadService: UploadService
    ): UploadRepository {
        return UploadRepositoryImp(uploadService)
    }


    @Singleton
    @Provides
    fun providePostRepository(
        prefHelper: PrefHelper,
        postService: PostService
    ): PostRepository {
        return PostRepositoryImp(prefHelper, postService)
    }
}