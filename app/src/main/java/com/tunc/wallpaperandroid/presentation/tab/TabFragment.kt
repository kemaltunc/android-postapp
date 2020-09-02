package com.tunc.wallpaperandroid.presentation.tab

import com.tunc.wallpaperandroid.R
import com.tunc.wallpaperandroid.core.BaseFragment
import com.tunc.wallpaperandroid.databinding.FragmentTabBinding
import com.tunc.wallpaperandroid.presentation.newpost.NewPostFragment
import com.tunc.wallpaperandroid.presentation.post.PostFragment
import com.tunc.wallpaperandroid.presentation.profile.ProfileFragment
import com.tunc.wallpaperandroid.utility.FragmentController.BackStackListener
import com.tunc.wallpaperandroid.utility.FragmentController.Navigator
import com.tunc.wallpaperandroid.utility.FragmentController.navigate
import com.tunc.wallpaperandroid.utility.MainController
import com.tunc.wallpaperandroid.utility.TabController
import com.tunc.wallpaperandroid.utility.extension.clickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class TabFragment : BaseFragment<TabViewModel, FragmentTabBinding>(), BackStackListener {

    override val layoutRes: Int = R.layout.fragment_tab
    override fun getViewModelClass() = TabViewModel::class.java

    private lateinit var postFragment: PostFragment
    private lateinit var profileFragment: ProfileFragment

    override fun define() {
        super.define()
        Navigator.addNavigator(TabController, childFragmentManager, R.id.tab_container)
        Navigator.setStackListener(this)

        postFragment = PostFragment.newInstance()
        profileFragment = ProfileFragment.newInstance()

        TabController.navigate(postFragment)

    }

    override fun initUI() {
        super.initUI()

        binding.tabBottomNavigation.setOnNavigationItemSelectedListener { menu ->

            when (menu.itemId) {
                R.id.menu_home -> {
                    TabController.navigate(postFragment)
                }
                R.id.menu_profile -> {
                    TabController.navigate(profileFragment)
                }

            }

            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun subscribeObservers() {
        super.subscribeObservers()
    }

    override fun clickListeners() {
        super.clickListeners()

        binding.newPostButton.clickListener {
            MainController.navigate(NewPostFragment.newInstance())
        }
    }

    override fun currentFragment(tag: String) {
        when (tag) {
            PostFragment::class.java.name -> {
                binding.tabBottomNavigation.selectedItemId = R.id.menu_home
            }
            ProfileFragment::class.java.name -> {
                binding.tabBottomNavigation.selectedItemId = R.id.menu_profile
            }
        }
    }

    companion object {
        fun newInstance() = TabFragment()
    }

}