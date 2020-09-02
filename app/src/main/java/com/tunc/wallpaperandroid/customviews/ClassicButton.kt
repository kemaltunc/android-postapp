package com.tunc.wallpaperandroid.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import com.tunc.wallpaperandroid.R
import kotlinx.android.synthetic.main.custom_button.view.*


class ClassicButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.custom_button, this, true)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClassicButton)

        with(typedArray) {
            val text = getString(R.styleable.ClassicButton_android_text)
            custom_button.run {
                setText(text)
            }
            recycle()
        }
    }


}
