package com.tunc.wallpaperandroid.presentation.newpost

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.tunc.wallpaperandroid.core.BaseViewModel
import com.tunc.wallpaperandroid.core.DataState
import com.tunc.wallpaperandroid.data.network.entity.request.PostRequest
import com.tunc.wallpaperandroid.domain.usecase.PostUseCase
import com.tunc.wallpaperandroid.domain.usecase.UploadUseCase
import com.tunc.wallpaperandroid.utility.LiveData.Event
import com.tunc.wallpaperandroid.utility.LiveData.SingleLiveEvent
import com.tunc.wallpaperandroid.utility.enums.FormError
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.File

@ExperimentalCoroutinesApi
class NewPostViewModel @ViewModelInject
constructor(
    private val uploadUseCase: UploadUseCase,
    private val postUseCase: PostUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _postData = SingleLiveEvent<Event<DataState<Boolean>>>()
    private val _formError = SingleLiveEvent<Event<FormError>>()


    val postData: LiveData<Event<DataState<Boolean>>>
        get() = _postData

    val formError: LiveData<Event<FormError>>
        get() = _formError

    fun createPost(
        imageFile: File?,
        deviceName: String?,
        description: String?
    ) {
        _formError.value = Event(when {
            imageFile == null -> {
                FormError.MISSING_FILE
            }
            deviceName.isNullOrEmpty() -> {
                FormError.MISSING_DEVICE_NAME
            }
            description.isNullOrEmpty() -> {
                FormError.MISSING_DESCRIPTION
            }
            else -> {

                execute { uploadUseCase.uploadFile(imageFile) }.onEach { dataState ->
                    when (dataState) {
                        is DataState.Success -> {

                            execute {
                                postUseCase.createPost(
                                    PostRequest(
                                        description,
                                        deviceName,
                                        dataState.data
                                    )
                                )
                            }.onEach { value ->
                                _postData.value = Event(value)
                            }.launchIn(viewModelScope)
                        }
                        is DataState.Loading -> {
                            showLoading.call()
                        }
                    }
                }.launchIn(viewModelScope)

                FormError.NOTHING
            }
        }
        )

    }


}
