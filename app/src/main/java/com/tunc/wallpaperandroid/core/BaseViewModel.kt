package com.tunc.wallpaperandroid.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunc.wallpaperandroid.data.exception.ApiErrorHandle
import com.tunc.wallpaperandroid.data.exception.ErrorModel
import com.tunc.wallpaperandroid.utility.LiveData.Event
import com.tunc.wallpaperandroid.utility.LiveData.SingleLiveEvent
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


abstract class BaseViewModel : ViewModel() {

    val errorHandle =
        SingleLiveEvent<Event<ErrorModel>>()
    val showLoading =
        SingleLiveEvent<Event<Boolean>>()
    val hideLoading =
        SingleLiveEvent<Event<Boolean>>()

    fun <T> execute(call: suspend () -> T): Flow<DataState<T>> = flow {
        emit(DataState.Loading)

        try {
            val result = call.invoke()
            emit(DataState.Success(result))

        } catch (e: Exception) {
            val error = ApiErrorHandle().traceErrorException(e)
            errorHandle.value = Event(error)
            emit(DataState.Error(error))
            hideLoading.value = Event(true)
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}