package com.tunc.wallpaperandroid.utility.extension

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import coil.transform.CircleCropTransformation
import com.tunc.wallpaperandroid.R
import java.io.File


//Url
fun ImageView.loadWithCache(path: String, circle: Boolean = false) {
    val circularProgressDrawable = CircularProgressDrawable(this.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f

    circularProgressDrawable.start()

    this.load(path) {
        /*   crossfade(true)*/
        placeholder(circularProgressDrawable)
        if (circle) transformations(CircleCropTransformation())
    }

    //Glide.with(this.context).load(path).into(this)


/*    Glide.with(this.context).load(path)
        .placeholder(circularProgressDrawable).into(this)*/
}

//File
fun ImageView.loadFile(file: File, circle: Boolean = false) {
    this.load(file) {
        crossfade(true)
        if (circle) transformations(CircleCropTransformation())
    }
}

//Res
fun ImageView.loadRes(resId: Int) {
    this.load(resId)
}