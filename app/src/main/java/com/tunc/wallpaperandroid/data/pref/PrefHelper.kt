package com.tunc.wallpaperandroid.data.pref

interface PrefHelper {
    fun saveAuthorizationToken(authKey: String?)
    fun getAuthorizationToken(): String?

    fun clear()
}