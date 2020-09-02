package com.tunc.wallpaperandroid.di

import android.content.Context
import com.tunc.wallpaperandroid.data.pref.PrefHelper
import com.tunc.wallpaperandroid.data.pref.PrefHelperImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext context: Context): PrefHelper = PrefHelperImp(context)
}