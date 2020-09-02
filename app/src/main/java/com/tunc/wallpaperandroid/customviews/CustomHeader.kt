package com.tunc.wallpaperandroid.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.tunc.wallpaperandroid.R
import com.tunc.wallpaperandroid.utility.extension.clickListener
import kotlinx.android.synthetic.main.custom_header.view.*

class CustomHeader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.custom_header, this, true)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomHeader)

        with(typedArray) {
            val text = getString(R.styleable.CustomHeader_android_text)
            header_text.run {
                setText(text)
            }
            recycle()
        }
    }

    fun clickBackButton(click: () -> Unit) {
        back_button.clickListener {
            click()
        }
    }

}
