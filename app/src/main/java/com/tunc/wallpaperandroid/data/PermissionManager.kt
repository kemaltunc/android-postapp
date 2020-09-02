package com.tunc.wallpaperandroid.data

import android.Manifest
import android.content.Intent
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.tbruyelle.rxpermissions2.RxPermissions

class PermissionManager(fragment: Fragment? = null, activity: FragmentActivity? = null) {

    private lateinit var rxPermissions: RxPermissions

    init {
        if (fragment != null) {
            rxPermissions = RxPermissions(fragment)
        } else if (activity != null) {
            rxPermissions = RxPermissions(activity)
        }

    }


    fun checkGalleryPermissions(): Boolean {
        return (rxPermissions.isGranted(Manifest.permission.READ_EXTERNAL_STORAGE)
                && rxPermissions.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                && rxPermissions.isGranted(Manifest.permission.CAMERA))
    }

    fun getCameraPermission(
        granted: () -> Unit,
        denied: () -> Unit
    ) {
        rxPermissions.request(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        ).subscribe { granted ->
            if (granted) {
                granted()
            } else {
                denied()
            }
        }
    }

    companion object {
        fun galleryIntent(): Intent {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            intent.type = "image/*"

            return intent
        }
    }

}