package com.tunc.wallpaperandroid.core

import com.tunc.wallpaperandroid.data.exception.ErrorModel

sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: ErrorModel) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}