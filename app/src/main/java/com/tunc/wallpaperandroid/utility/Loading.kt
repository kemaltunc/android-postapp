package com.tunc.wallpaperandroid.utility

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.tunc.wallpaperandroid.R
import com.tunc.wallpaperandroid.utility.extension.gone
import kotlinx.android.synthetic.main.loading_bar.*

object Loading {
    fun showLoadingDialog(context: Context): Dialog {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.loading_bar)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
        dialog.loading.show()

        return dialog
    }
}