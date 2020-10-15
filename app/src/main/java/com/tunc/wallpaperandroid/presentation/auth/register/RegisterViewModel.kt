package com.tunc.wallpaperandroid.presentation.auth.register

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.tunc.wallpaperandroid.core.BaseViewModel
import com.tunc.wallpaperandroid.core.DataState
import com.tunc.wallpaperandroid.data.network.entity.request.RegisterRequest
import com.tunc.wallpaperandroid.domain.entity.UserEntity
import com.tunc.wallpaperandroid.domain.usecase.AccountUseCase
import com.tunc.wallpaperandroid.domain.usecase.UploadUseCase
import com.tunc.wallpaperandroid.utility.LiveData.Event
import com.tunc.wallpaperandroid.utility.enums.FormError
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.File

@ExperimentalCoroutinesApi
class RegisterViewModel @ViewModelInject
constructor(
    private val accountUseCase: AccountUseCase,
    private val uploadUseCase: UploadUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _userData = MutableLiveData<Event<DataState<UserEntity>>>()
    val userData: LiveData<Event<DataState<UserEntity>>> = _userData

    val formError = MutableLiveData<Event<FormError>>()


    fun register(
        name: String?,
        surname: String?,
        email: String?,
        password: String?,
        rePassword: String?,
        file: File?
    ) {

        formError.value = Event(when {
            name.isNullOrEmpty() -> {
                FormError.MISSING_NAME
            }
            surname.isNullOrEmpty() -> {
                FormError.MISSING_SURNAME
            }
            email.isNullOrEmpty() -> {
                FormError.MISSING_EMAIL
            }
            password.isNullOrEmpty() -> {
                FormError.MISSING_PASSWORD
            }
            rePassword.isNullOrEmpty() -> {
                FormError.MISSING_RE_PASSWORD
            }
            password != rePassword -> {
                FormError.NOT_MATCH_PASSWORD
            }

            else -> {

                file?.let {
                    execute { uploadUseCase.uploadFile(file) }.onEach { dataState ->
                        when (dataState) {
                            is DataState.Success -> {
                                registerFinally(name, surname, email, password, dataState.data)
                            }
                            is DataState.Loading -> {
                                showLoading.value = Event(true)
                            }
                        }


                    }.launchIn(viewModelScope)
                } ?: registerFinally(name, surname, email, password)

                FormError.NOTHING

            }
        })

    }

    private fun registerFinally(
        name: String,
        surname: String,
        email: String,
        password: String,
        imagePath: String = ""
    ) {
        val registerRequest =
            RegisterRequest(
                email, password, name, surname, imagePath
            )

        execute { accountUseCase.register(registerRequest) }.onEach { value ->
            _userData.value = Event(value)
        }.launchIn(viewModelScope)
    }


}


