package com.tunc.wallpaperandroid.data.pref

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PrefHelperImp @Inject constructor(context: Context) : PrefHelper {

    private var mPrefs: SharedPreferences =
        context.getSharedPreferences(PREFHELPER, Context.MODE_PRIVATE)


    override fun saveAuthorizationToken(authKey: String?) {
        mPrefs.edit().putString(AUTHORIZATON_PREF_KEY, "Bearer $authKey").apply()
    }

    override fun getAuthorizationToken(): String? {
        return mPrefs.getString(AUTHORIZATON_PREF_KEY, "")
    }

    override fun clear() {
        mPrefs.edit().clear().apply()
    }

    companion object {
        const val PREFHELPER = "Pref"
        const val AUTHORIZATON_PREF_KEY = "authorizationPref"
    }
}