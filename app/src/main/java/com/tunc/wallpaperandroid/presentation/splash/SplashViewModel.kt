package com.tunc.wallpaperandroid.presentation.splash

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.tunc.wallpaperandroid.core.BaseViewModel
import com.tunc.wallpaperandroid.domain.usecase.AccountUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class SplashViewModel @ViewModelInject
constructor(
    private val accountUseCase: AccountUseCase
) : BaseViewModel() {


    val isLogin = MutableLiveData<Boolean>()


    fun setStateEvent(splashStateEvent: SplashStateEvent) {
        viewModelScope.launch {
            when (splashStateEvent) {
                is SplashStateEvent.StartApp -> {
                    isLogin.value = accountUseCase.isLogined()
                }

                is SplashStateEvent.CheckToken -> {
                    //
                }
            }
        }


    }

}

sealed class SplashStateEvent {
    object StartApp : SplashStateEvent()

    object CheckToken : SplashStateEvent()

}