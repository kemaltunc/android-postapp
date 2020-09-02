package com.tunc.wallpaperandroid.presentation.post

import androidx.lifecycle.Observer
import com.tunc.wallpaperandroid.R
import com.tunc.wallpaperandroid.core.BaseFragment
import com.tunc.wallpaperandroid.core.DataState
import com.tunc.wallpaperandroid.databinding.FragmentPostBinding
import com.tunc.wallpaperandroid.domain.entity.PostEntity
import com.tunc.wallpaperandroid.presentation.adapter.PostAdapter
import com.tunc.wallpaperandroid.presentation.adapter.PostInterface
import com.tunc.wallpaperandroid.presentation.settings.SettingsFragment
import com.tunc.wallpaperandroid.utility.FragmentController.Navigator
import com.tunc.wallpaperandroid.utility.LiveData.EventObserver
import com.tunc.wallpaperandroid.utility.extension.clickListener
import com.tunc.wallpaperandroid.utility.extension.vertical
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class PostFragment : BaseFragment<PostViewModel, FragmentPostBinding>(), PostInterface {
    override val layoutRes: Int = R.layout.fragment_post

    override fun getViewModelClass() = PostViewModel::class.java

    private val postAdapter by lazy { PostAdapter(this) }

    private var page = 0
    private var sendRequest = true

    override fun define() {
        super.define()

        viewModel.getPost(++page)

    }

    override fun initUI() {
        super.initUI()
        binding.postRv.vertical(postAdapter, bottomDetect = {
            if (sendRequest) {
                viewModel.getPost(++page)
            }
        })

        binding.swipeRefresh.setOnRefreshListener {
            resetData()
        }
    }

    private fun resetData() {
        page = 0
        postAdapter.items = emptyList()
        sendRequest = true
        viewModel.getPost(++page)
    }

    override fun subscribeObservers() {
        super.subscribeObservers()

        viewModel.posts.observe(viewLifecycleOwner, EventObserver { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    postAdapter.items += dataState.data
                    hideLoading()
                    if (dataState.data.isEmpty()) {
                        sendRequest = false
                    }
                }
                is DataState.Loading -> {
                    if (page == 1) showLoading()
                }
            }
        })
    }


    override fun clickListeners() {
        super.clickListeners()

        binding.settingsButton.clickListener {
            Navigator.navigate(SettingsFragment.newInstance())
        }
    }

    override fun hideLoading() {
        super.hideLoading()
        binding.swipeRefresh.isRefreshing = false
    }

    override fun like(item: PostEntity) {
        viewModel.likePost(item)
    }

    override fun comment(item: PostEntity) {

    }

    companion object {
        fun newInstance() = PostFragment()
    }

}