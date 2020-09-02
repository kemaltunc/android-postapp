package com.tunc.wallpaperandroid.presentation.tab

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.tunc.wallpaperandroid.core.BaseViewModel
import com.tunc.wallpaperandroid.domain.usecase.AccountUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class TabViewModel @ViewModelInject
constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {



}