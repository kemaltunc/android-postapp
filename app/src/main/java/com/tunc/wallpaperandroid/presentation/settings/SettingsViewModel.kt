package com.tunc.wallpaperandroid.presentation.settings

import androidx.hilt.lifecycle.ViewModelInject
import com.tunc.wallpaperandroid.core.BaseViewModel
import com.tunc.wallpaperandroid.domain.usecase.AccountUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class SettingsViewModel @ViewModelInject
constructor(
    private val accountUseCase: AccountUseCase
) : BaseViewModel() {

    fun logout(){
        accountUseCase.logout()
    }

}