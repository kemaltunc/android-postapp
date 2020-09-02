package com.tunc.wallpaperandroid.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.tunc.wallpaperandroid.R
import kotlinx.android.synthetic.main.custom_button.view.*

class Line @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.custom_line, this, true)

    }




}
