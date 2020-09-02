package com.tunc.wallpaperandroid.utility.FragmentController

import android.app.Activity
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.tunc.wallpaperandroid.utility.Controllers

class FragmentController(
    private val activity: Activity,
    private val containerId: Int
) {

    private val TAG = "FragmentController"
    private val appCompatActivity = (activity as AppCompatActivity)

    private lateinit var stackListener: BackStackListener


    class NavigatorDetail(
        var navigatorName: String,
        var childContainer: Int,
        var fm: FragmentManager
    )

    class Stack(
        var tag: String,
        var fm: FragmentManager
    )

    var fragStackList = ArrayList<Stack>()
    var navigatorList = ArrayList<NavigatorDetail>()


    fun startFragment(fragment: Fragment, history: Boolean) {
        fragStackList.clear()
        navigatorList.clear()

        navigatorList.add(
            NavigatorDetail(
                Controllers.MainController,
                containerId,
                appCompatActivity.supportFragmentManager
            )
        )

        fragmentController = this
        navigate(fragment, navigatorName = Controllers.MainController, history = history)
        appCompatActivity.onBackPressedDispatcher.addCallback(appCompatActivity, onBackListener)

    }

    fun addNavigator(navigatorName: String, fm: FragmentManager, childContainer: Int) {
        val index = navigatorList.indexOfFirst { it.navigatorName == navigatorName }

        if (index != -1) {
            navigatorList.removeAt(index)
        }
        navigatorList.add(
            NavigatorDetail(navigatorName, childContainer, fm)
        )
    }

    fun setStackListener(stackListener: BackStackListener) {
        this.stackListener = stackListener
    }

    private val onBackListener = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {

            if (fragStackList.size > 1) {

                val findNav = fragStackList.takeLast(2).first()
                val currentNav = fragStackList.last().fm

                if (findNav.fm == currentNav) {
                    findNav.fm.popBackStack(findNav.tag, 0)
                    if (this@FragmentController::stackListener.isInitialized) {
                        stackListener.currentFragment(findNav.tag)
                    }
                } else {
                    currentNav.popBackStack()
                }
                fragStackList.remove(fragStackList.last())

            } else {
                activity.finish()
            }

        }
    }

    fun navigate(
        fragment: Fragment,
        navigatorName: String = Controllers.MainController,
        label: String = "",
        history: Boolean = true,
        clearHistory: Boolean = false
    ) {

        val findNav = navigatorList.find { it.navigatorName == navigatorName }

        if (findNav != null) {

            val fragName = fragment.javaClass.name + label

            val index = fragStackList.indexOfFirst { it.tag == fragName }

            if (index != -1 && !clearHistory) {
                val findFrag = getFragmentByTag(fragName, findNav.fm)
                removeFragment(findNav.fm, findFrag, index)
            }

            if (clearHistory) removeHistory(findNav.fm)

            val transaction = findNav.fm.beginTransaction()
                .replace(findNav.childContainer, fragment, fragName)

            if (history) {
                transaction.addToBackStack(fragName)
                fragStackList.add(Stack(fragName, findNav.fm))
            }

            transaction.commit()
        }

    }

    fun navigateUp() {
        activity.onBackPressed()
    }

    private fun getFragmentByTag(tag: String, fm: FragmentManager): Fragment? {
        return fm.findFragmentByTag(tag)
    }


    private fun removeFragment(fragmentManager: FragmentManager, fragment: Fragment?, index: Int) {
        fragStackList.removeAt(index)
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit()
        }

    }

    private fun removeHistory(fragmentManager: FragmentManager) {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        fragStackList.clear()
    }

    companion object {
        private lateinit var fragmentController: FragmentController

        val controller: FragmentController
            get() = fragmentController
    }
}

val Fragment.Navigator: FragmentController get() = FragmentController.controller

fun String.navigate(
    fragment: Fragment, label: String = "",
    history: Boolean = true,
    clearHistory: Boolean = false
) {
    FragmentController.controller.navigate(fragment, this,label, history, clearHistory)
}

interface BackStackListener {
    fun currentFragment(tag: String)
}