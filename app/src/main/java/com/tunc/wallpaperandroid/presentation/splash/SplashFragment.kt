package com.tunc.wallpaperandroid.presentation.splash

import androidx.lifecycle.Observer
import com.tunc.wallpaperandroid.R
import com.tunc.wallpaperandroid.core.BaseFragment
import com.tunc.wallpaperandroid.databinding.FragmentSplashBinding
import com.tunc.wallpaperandroid.presentation.auth.login.LoginFragment
import com.tunc.wallpaperandroid.presentation.tab.TabFragment
import com.tunc.wallpaperandroid.utility.FragmentController.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class SplashFragment : BaseFragment<SplashViewModel, FragmentSplashBinding>() {

    override val layoutRes: Int = R.layout.fragment_splash
    override fun getViewModelClass() = SplashViewModel::class.java

    override fun initUI() {
        super.initUI()


        viewModel.setStateEvent(SplashStateEvent.StartApp)

    }


    override fun subscribeObservers() {
        super.subscribeObservers()

        viewModel.isLogin.observe(viewLifecycleOwner, Observer { result ->
            if (result) {
                Navigator.navigate(TabFragment.newInstance(), history = false)
            } else {
                Navigator.navigate(LoginFragment.newInstance())
            }
        })
    }

    companion object {
        fun newInstance() = SplashFragment()
    }
}