package com.tunc.wallpaperandroid

import android.app.Application
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp
import rx_activity_result2.RxActivityResult

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        RxActivityResult.register(this)
    }
}