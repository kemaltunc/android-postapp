package com.tunc.wallpaperandroid.customviews

import android.content.Context
import android.content.res.TypedArray
import android.os.Parcelable
import android.text.InputType.TYPE_TEXT_VARIATION_NORMAL
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.tunc.wallpaperandroid.R
import com.tunc.wallpaperandroid.utility.SavedState
import kotlinx.android.synthetic.main.custom_edittext.view.*


class ClassicEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.custom_edittext, this, true)


        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClassicEditText)

        with(typedArray) {
            val hint = getString(R.styleable.ClassicEditText_android_hint)
            val hintColor = getColor(
                R.styleable.ClassicEditText_hintTextColor,
                ContextCompat.getColor(context, R.color.dark_grey)
            )

            val textColor = getColor(
                R.styleable.ClassicEditText_android_textColor,
                ContextCompat.getColor(context, R.color.white)
            )


            val typedCount = typedArray.indexCount

            for (i in 0 until typedCount) {
                when (val attr = typedArray.getIndex(i)) {
                    R.styleable.ClassicEditText_android_inputType -> setInputType(
                        typedArray,
                        attr
                    )
                }
            }

            custom_edittext.run {
                setHint(hint)
                setHintTextColor(hintColor)
                setTextColor(textColor)
            }
            recycle()
        }

    }

    val text: String
        get() = custom_edittext.text.toString()

    fun disable() {
        custom_edittext.isEnabled = false
    }

    fun enable() {
        custom_edittext.isEnabled = true
    }

    private fun setInputType(typedArray: TypedArray, attr: Int) {
        val inputType = typedArray.getInt(attr, TYPE_TEXT_VARIATION_NORMAL)
        custom_edittext.inputType = inputType
    }

    @Suppress("UNCHECKED_CAST")
    public override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()
        val ss = superState?.let { SavedState(it) }
        ss?.childrenStates = SparseArray()
        for (i in 0 until childCount) {
            getChildAt(i).saveHierarchyState(ss?.childrenStates as SparseArray<Parcelable>)
        }
        return ss
    }

    @Suppress("UNCHECKED_CAST")
    public override fun onRestoreInstanceState(state: Parcelable) {
        val ss = state as SavedState
        super.onRestoreInstanceState(ss.superState)
        for (i in 0 until childCount) {
            getChildAt(i).restoreHierarchyState(ss.childrenStates as SparseArray<Parcelable>)
        }
    }

    override fun dispatchSaveInstanceState(container: SparseArray<Parcelable>) {
        dispatchFreezeSelfOnly(container)
    }

    override fun dispatchRestoreInstanceState(container: SparseArray<Parcelable>) {
        dispatchThawSelfOnly(container)
    }


}
