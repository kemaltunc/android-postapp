package com.tunc.wallpaperandroid.presentation.profile

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.tunc.wallpaperandroid.core.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ProfileViewModel @ViewModelInject
constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {


}