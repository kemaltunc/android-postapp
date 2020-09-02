package com.tunc.wallpaperandroid.di

import android.app.Application
import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import com.tunc.wallpaperandroid.BuildConfig
import com.tunc.wallpaperandroid.data.network.service.AccountService
import com.tunc.wallpaperandroid.data.network.service.PostService
import com.tunc.wallpaperandroid.data.network.service.UploadService
import com.tunc.wallpaperandroid.data.pref.PrefHelper
import com.tunc.wallpaperandroid.utility.NetworkUtil.CACHE_SIZE
import com.tunc.wallpaperandroid.utility.NetworkUtil.CONNECT_TIME_OUT
import com.tunc.wallpaperandroid.utility.NetworkUtil.READ_TIME_OUT
import com.tunc.wallpaperandroid.utility.NetworkUtil.RetrofitHeader
import com.tunc.wallpaperandroid.utility.NetworkUtil.RetrofitLogging
import com.tunc.wallpaperandroid.utility.NetworkUtil.WRITE_TIME_OUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(
        application: Application,
        @ApplicationContext context: Context,
        prefHelper: PrefHelper
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
            .cache(Cache(context.cacheDir, CACHE_SIZE))
            .addInterceptor(RetrofitHeader(context, prefHelper))
            .addInterceptor(RetrofitLogging())
            .addNetworkInterceptor(StethoInterceptor())
            .addNetworkInterceptor(ChuckInterceptor(application))
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create().asLenient()).build()
    }


    @Provides
    @Singleton
    fun provideAccountService(retrofit: Retrofit): AccountService {
        return retrofit.create(AccountService::class.java)
    }



    @Provides
    @Singleton
    fun providePostService(retrofit: Retrofit): PostService {
        return retrofit.create(PostService::class.java)
    }

    @Provides
    @Singleton
    fun provideUploadService(retrofit: Retrofit): UploadService {
        return retrofit.create(UploadService::class.java)
    }


}
