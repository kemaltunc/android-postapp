package com.tunc.wallpaperandroid.presentation.post

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tunc.wallpaperandroid.core.BaseViewModel
import com.tunc.wallpaperandroid.core.DataState
import com.tunc.wallpaperandroid.domain.entity.PostEntity
import com.tunc.wallpaperandroid.domain.usecase.PostUseCase
import com.tunc.wallpaperandroid.utility.LiveData.Event
import com.tunc.wallpaperandroid.utility.LiveData.SingleLiveEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
class PostViewModel @ViewModelInject
constructor(
    private val postUseCase: PostUseCase
) : BaseViewModel() {

    private val _posts = SingleLiveEvent<Event<DataState<List<PostEntity>>>>()

    val posts: LiveData<Event<DataState<List<PostEntity>>>>
        get() = _posts


    fun getPost(page: Int) {
        execute { postUseCase.getAll(page) }.onEach { dataState ->
            _posts.value = Event(dataState)
        }.launchIn(viewModelScope)
    }

    fun likePost(item: PostEntity) {
        execute { postUseCase.like(item) }.onEach { dataState ->

        }.launchIn(viewModelScope)
    }

}