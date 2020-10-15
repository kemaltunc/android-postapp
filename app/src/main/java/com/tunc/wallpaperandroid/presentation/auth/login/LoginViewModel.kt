package com.tunc.wallpaperandroid.presentation.auth.login

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.tunc.wallpaperandroid.core.BaseViewModel
import com.tunc.wallpaperandroid.core.DataState
import com.tunc.wallpaperandroid.data.network.entity.request.LoginRequest
import com.tunc.wallpaperandroid.domain.entity.UserEntity
import com.tunc.wallpaperandroid.domain.usecase.AccountUseCase
import com.tunc.wallpaperandroid.utility.LiveData.Event
import com.tunc.wallpaperandroid.utility.enums.FormError
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
class LoginViewModel @ViewModelInject
constructor(
    private val accountUseCase: AccountUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _userData = MutableLiveData<Event<DataState<UserEntity>>>()

    private val _formError = MutableLiveData<Event<FormError>>()

    val dataState: LiveData<Event<DataState<UserEntity>>>
        get() = _userData

    val formError: LiveData<Event<FormError>>
        get() = _formError

    fun login(email: String?, password: String?) {

        _formError.value = Event(when {
            email.isNullOrEmpty() -> FormError.MISSING_EMAIL
            password.isNullOrEmpty() -> FormError.MISSING_PASSWORD
            else -> {
                val loginRequest = LoginRequest(
                    email, password
                )
                execute { accountUseCase.login(loginRequest) }.onEach { dataState ->
                    _userData.value = Event(dataState)
                }.launchIn(viewModelScope)

                FormError.NOTHING
            }
        }
        )

    }
}
