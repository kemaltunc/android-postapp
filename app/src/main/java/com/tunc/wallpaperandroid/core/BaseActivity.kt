package com.tunc.wallpaperandroid.core


import android.app.Dialog
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tunc.wallpaperandroid.utility.Loading


abstract class BaseActivity : AppCompatActivity() {

    private lateinit var loading: Dialog

    open fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    open fun showMessage(messageId: Int) {
        Toast.makeText(applicationContext, getString(messageId), Toast.LENGTH_SHORT).show()
    }


    open fun showLoading() {
        if (!this::loading.isInitialized) {
            loading = Loading.showLoadingDialog(this)
        } else {
            if (!loading.isShowing) {
                loading.show()
            }
        }
    }

    open fun hideLoading() {
        if (this::loading.isInitialized) {
            loading.dismiss()
            loading.cancel()
        }
    }

}