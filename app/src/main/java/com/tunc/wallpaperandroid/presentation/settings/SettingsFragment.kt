package com.tunc.wallpaperandroid.presentation.settings

import com.tunc.wallpaperandroid.R
import com.tunc.wallpaperandroid.core.BaseFragment
import com.tunc.wallpaperandroid.databinding.FragmentSettingsBinding
import com.tunc.wallpaperandroid.presentation.auth.login.LoginFragment
import com.tunc.wallpaperandroid.utility.FragmentController.Navigator
import com.tunc.wallpaperandroid.utility.extension.clickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@AndroidEntryPoint
@ExperimentalCoroutinesApi
class SettingsFragment : BaseFragment<SettingsViewModel, FragmentSettingsBinding>() {
    override val layoutRes: Int = R.layout.fragment_settings

    override fun getViewModelClass() = SettingsViewModel::class.java

    override fun clickListeners() {
        super.clickListeners()

        binding.header.clickBackButton {
            Navigator.navigateUp()
        }

        binding.logoutBtn.clickListener {
            viewModel.logout()
            Navigator.navigate(LoginFragment.newInstance(), clearHistory = true)
        }

    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}