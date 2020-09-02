package com.tunc.wallpaperandroid.presentation.auth.login

import com.tunc.wallpaperandroid.R
import com.tunc.wallpaperandroid.core.BaseFragment
import com.tunc.wallpaperandroid.core.DataState
import com.tunc.wallpaperandroid.databinding.FragmentLoginBinding
import com.tunc.wallpaperandroid.domain.entity.UserEntity
import com.tunc.wallpaperandroid.presentation.auth.register.RegisterFragment
import com.tunc.wallpaperandroid.presentation.tab.TabFragment
import com.tunc.wallpaperandroid.utility.FragmentController.Navigator
import com.tunc.wallpaperandroid.utility.LiveData.EventObserver
import com.tunc.wallpaperandroid.utility.enums.FormError
import com.tunc.wallpaperandroid.utility.extension.clickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@AndroidEntryPoint
@ExperimentalCoroutinesApi
class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {

    override val layoutRes: Int = R.layout.fragment_login
    override fun getViewModelClass() = LoginViewModel::class.java


    override fun initUI() {
        super.initUI()

    }

    override fun subscribeObservers() {
        super.subscribeObservers()

        viewModel.dataState.observe(viewLifecycleOwner, EventObserver { dataState ->
            when (dataState) {
                is DataState.Success<UserEntity> -> {
                    hideLoading()
                    Navigator.navigate(TabFragment.newInstance(), history = false)
                }
                is DataState.Loading -> {
                    showLoading()
                }
            }
        })

        viewModel.formError.observe(viewLifecycleOwner, EventObserver { state ->
            if (state != FormError.NOTHING) {
                showMessage(getString(R.string.cannot_be_black))
            }
        })

    }

    override fun clickListeners() {
        binding.registerButton.clickListener {
            Navigator.navigate(RegisterFragment.newInstance())
        }

        binding.loginButton.clickListener {
            viewModel.login(binding.emailEd.text, binding.passwordEd.text)
        }
    }

    companion object {
        fun newInstance() = LoginFragment()
    }

}