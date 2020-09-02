package com.tunc.wallpaperandroid.core

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder<Data, V : ViewDataBinding> : RecyclerView.ViewHolder,
    LayoutContainer {

    var onViewClick: ((Data, View) -> Unit) = { _, _ -> }

    constructor(parent: ViewGroup, @LayoutRes layoutId: Int) : this(
        LayoutInflater.from(parent.context).inflate(
            layoutId,
            parent,
            false
        )

    )

    constructor(itemView: View) : super(itemView)

    override val containerView: View?
        get() = this.itemView


    val context: Context
        get() = this.itemView.context


    abstract fun bind(item: Data, binding: V)

    internal fun bindItem(item: Data, onItemClick: (Data) -> Unit) {
        with(itemView) {
            setOnClickListener { onItemClick(item) }
            DataBindingUtil.bind<V>(itemView)?.let { bind(item, it) }
        }
    }

    internal fun setOnViewClick(onViewClick: ((Data, View) -> Unit)): BaseViewHolder<Data, *> {
        this.onViewClick = onViewClick
        return this
    }
}