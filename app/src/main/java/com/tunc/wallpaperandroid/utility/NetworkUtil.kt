package com.tunc.wallpaperandroid.utility

import android.content.Context
import android.net.ConnectivityManager
import com.tunc.wallpaperandroid.data.pref.PrefHelper
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

object NetworkUtil {

    const val CACHE_SIZE: Long = 10 * 1024 * 1024
    const val MAX_STALE: Int = 60 * 60 * 24 * 28

    const val CONNECT_TIME_OUT: Long = 30L
    const val READ_TIME_OUT: Long = 30L
    const val WRITE_TIME_OUT: Long = 30L

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    @JvmStatic
    fun RetrofitHeader(context: Context, prefHelper: PrefHelper): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", prefHelper.getAuthorizationToken() ?: "")
                    .addHeader("Content-Type", "application/json")
                    .build()

                if (!isNetworkAvailable(context)) {
                    val maxStale = MAX_STALE
                    return chain.proceed(
                        chain.request().newBuilder()
                            .header(
                                "Cache-Control",
                                "public, only-if-cached, max-stale=$maxStale"
                            )
                            .build()
                    )
                }
                return chain.proceed(request)
            }
        }

    }

    @JvmStatic
    fun RetrofitLogging(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return loggingInterceptor
    }


}