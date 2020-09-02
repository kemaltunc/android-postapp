package com.tunc.wallpaperandroid.core

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kotlin.properties.Delegates


abstract class BaseAdapter<Data> :
    ListAdapter<Data, BaseViewHolder<Data, *>>(DiffCallback<Data>()) {


    private var onItemClick: ((Data) -> Unit) = {}

    private var onViewClick: ((Data, View) -> Unit) = { _, _ -> }

    var hideLoading = true


    var items: List<Data> by Delegates.observable(emptyList()) { prop, old, new ->
        this.submitList(new)

        hideLoading = old == new

        if (hideLoading) {
            removeLoading()
        }
    }


    abstract override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Data, *>


    override fun onBindViewHolder(holder: BaseViewHolder<Data, *>, position: Int) {
        holder.setOnViewClick(onViewClick).bindItem(getItem(position), onItemClick)
    }

    private fun removeLoading() {
        this.notifyItemChanged(itemCount - 1)
    }

    fun onItemClick(onClick: ((Data) -> Unit)): BaseAdapter<Data> {
        this.onItemClick = onClick
        return this
    }


    fun onViewClick(onClick: ((Data, View) -> Unit)): BaseAdapter<Data> {
        this.onViewClick = onClick
        return this
    }


    class DiffCallback<Data> : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data) =
            oldItem.hashCode() == newItem.hashCode()

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Data, newItem: Data) = oldItem == newItem
    }

}