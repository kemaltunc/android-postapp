package com.tunc.wallpaperandroid.domain.entity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

data class PostEntity(
    var postId: String?,
    var sender: UserEntity?,
    var deviceName: String?,
    var description: String?,
    var imagePath: String?,
    private var _commentCount: Int?,
    private var _likeCount: Int?,
    private var _isLiked: Boolean?
) : BaseObservable() {


    var likeCount: Int
        @Bindable get() = _likeCount ?: 0
        set(value) {
            _likeCount = value
            notifyPropertyChanged(BR.likeCount)
        }


    var commentCount: Int
        @Bindable get() = _commentCount ?: 0
        set(value) {
            _commentCount = value
            notifyPropertyChanged(BR.commentCount)

        }

    var liked: Boolean
        @Bindable get() = _isLiked ?: false
        set(value) {
            _isLiked = value
            if (value) likeCount += 1 else likeCount -= 1
            notifyPropertyChanged(BR.liked)
        }
}