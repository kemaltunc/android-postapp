package com.tunc.wallpaperandroid.presentation.main

import android.os.Bundle
import com.tunc.wallpaperandroid.R
import com.tunc.wallpaperandroid.core.BaseActivity
import com.tunc.wallpaperandroid.presentation.splash.SplashFragment
import com.tunc.wallpaperandroid.utility.FragmentController.FragmentController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MainActivity : BaseActivity() {

    private val navigator = FragmentController(this, R.id.main_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigator.startFragment(SplashFragment.newInstance(), history = false)

    }

}