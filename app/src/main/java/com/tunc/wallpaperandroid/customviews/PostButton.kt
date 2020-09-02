package com.tunc.wallpaperandroid.customviews

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.tunc.wallpaperandroid.databinding.CustomPostButtonBinding

class PostButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var binding: CustomPostButtonBinding =
        CustomPostButtonBinding.inflate(LayoutInflater.from(context), this, true)

    fun setImg(drawable: Drawable?) {
        binding.buttonImage.background = drawable
    }

    fun setCount(count: Int) {
        binding.countTv.text = count.toString()

        if (count > 0) {
            binding.countTv.visibility = View.VISIBLE
        } else {
            binding.countTv.visibility = View.GONE
        }

    }
}