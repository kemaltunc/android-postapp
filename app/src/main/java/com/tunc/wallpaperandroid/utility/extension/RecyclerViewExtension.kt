package com.tunc.wallpaperandroid.utility.extension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tunc.wallpaperandroid.utility.EndlessOnScrollListener

fun RecyclerView.vertical(
    adapter: RecyclerView.Adapter<*>,
    layoutManager: RecyclerView.LayoutManager? = null,
    bottomDetect: () -> Unit = {}
) {
    this.run {
        this.layoutManager = layoutManager ?: LinearLayoutManager(context)
        this.adapter = adapter
    }

    this.addOnScrollListener(object :
        EndlessOnScrollListener(this@vertical.layoutManager as LinearLayoutManager) {
        override fun onScrolledToEnd() {
            bottomDetect()
        }
    })
}