package com.tunc.wallpaperandroid.presentation.adapter

import android.view.ViewGroup
import com.tunc.wallpaperandroid.R
import com.tunc.wallpaperandroid.core.BaseAdapter
import com.tunc.wallpaperandroid.core.BaseViewHolder
import com.tunc.wallpaperandroid.databinding.CellLoadingBinding
import com.tunc.wallpaperandroid.databinding.PostCellBinding
import com.tunc.wallpaperandroid.domain.entity.PostEntity
import com.tunc.wallpaperandroid.utility.extension.clickListenerWithoutAnimate
import com.tunc.wallpaperandroid.utility.extension.gone
import com.tunc.wallpaperandroid.utility.extension.show

class PostAdapter(private val postInterface: PostInterface) : BaseAdapter<PostEntity>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<PostEntity, *> {

        return when (viewType) {
            PostTypes.NORMAL.type -> {
                PostViewHolder(parent, postInterface)
            }
            PostTypes.SHOW_LOADING.type -> {
                LoadingHolder(parent, true)
            }
            PostTypes.HIDE_LOADING.type -> {
                LoadingHolder(parent, false)
            }
            else -> {
                PostViewHolder(parent, postInterface)
            }
        }


    }

    class LoadingHolder(
        parent: ViewGroup,
        private val visibility: Boolean
    ) : BaseViewHolder<PostEntity, CellLoadingBinding>(parent, R.layout.cell_loading) {
        override fun bind(item: PostEntity, binding: CellLoadingBinding) {
            if (visibility) {
                itemView.show()
            } else {
                itemView.gone()
            }
        }

    }

    class PostViewHolder(
        parent: ViewGroup,
        private val postInterface: PostInterface
    ) : BaseViewHolder<PostEntity, PostCellBinding>(parent, R.layout.post_cell) {
        override fun bind(item: PostEntity, binding: PostCellBinding) {
            binding.data = item
            binding.executePendingBindings()

            binding.likeTv.clickListenerWithoutAnimate {
                item.liked = !item.liked
                postInterface.like(item)
            }

            binding.commentTv.clickListenerWithoutAnimate {
                postInterface.comment(item)
            }


        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (itemCount > 1 && itemCount == position + 1) {
            if (!hideLoading) PostTypes.SHOW_LOADING.type else PostTypes.HIDE_LOADING.type
        } else PostTypes.NORMAL.type
    }

    enum class PostTypes(var type: Int) {
        NORMAL(0),
        SHOW_LOADING(2),
        HIDE_LOADING(3)
    }
}