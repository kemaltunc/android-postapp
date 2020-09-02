package com.tunc.wallpaperandroid.presentation.profile

import com.tunc.wallpaperandroid.R
import com.tunc.wallpaperandroid.core.BaseFragment
import com.tunc.wallpaperandroid.databinding.FragmentPostBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class ProfileFragment : BaseFragment<ProfileViewModel, FragmentPostBinding>() {
    override val layoutRes: Int = R.layout.fragment_profile

    override fun getViewModelClass() = ProfileViewModel::class.java


    companion object {
        fun newInstance() = ProfileFragment()
    }

}