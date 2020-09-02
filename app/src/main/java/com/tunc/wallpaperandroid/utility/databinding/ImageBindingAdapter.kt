package com.tunc.wallpaperandroid.utility.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.tunc.wallpaperandroid.utility.extension.loadWithCache

@BindingAdapter("circleImage")
fun circleLoadImage(view: ImageView, url: String) {
    view.loadWithCache(url, true)
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    view.loadWithCache(url)
}