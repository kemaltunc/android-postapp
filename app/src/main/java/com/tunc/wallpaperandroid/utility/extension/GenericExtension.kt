package com.tunc.wallpaperandroid.utility.extension

import android.app.Activity
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.ArrayAdapter
import com.tunc.wallpaperandroid.utility.PathUtils
import java.io.File

fun String.Logd(message: String) {
    Log.d(this, message)
}

fun URItoFile(activity: Activity, uri: Uri): File {
    var filePath: String
    val cursor = activity.contentResolver.query(uri, null, null, null, null)
    if (cursor == null) {
        filePath = uri.path!!
    } else {
        try {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            filePath = cursor.getString(idx)
            cursor.close()
        } catch (e: RuntimeException) {
            filePath = PathUtils.getFilePathForN(activity, uri)
        }
    }
    return File(filePath)
}
