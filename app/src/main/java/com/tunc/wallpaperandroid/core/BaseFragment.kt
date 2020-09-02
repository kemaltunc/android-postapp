package com.tunc.wallpaperandroid.core

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tunc.wallpaperandroid.utility.LiveData.EventObserver
import com.tunc.wallpaperandroid.utility.extension.Logd


abstract class BaseFragment<ViewModel : BaseViewModel, ViewBinding : ViewDataBinding> : Fragment() {

    @get:LayoutRes
    protected abstract val layoutRes: Int


    private var baseActivity: BaseActivity? = null


    protected val viewModel: ViewModel by lazy { ViewModelProvider(this).get(getViewModelClass()) }
    protected abstract fun getViewModelClass(): Class<ViewModel>


    lateinit var binding: ViewBinding


    private var screenCreated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!screenCreated) define()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!this::binding.isInitialized) {
            binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
            binding.lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (!screenCreated) {
            initUI()

        } else {
            reCreated()
        }
        screenCreated = true
        subscribeObservers()
        clickListeners()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            this.baseActivity = context
        }
    }

    open fun define() {}
    open fun initUI() {}
    open fun reCreated() {}

    open fun clickListeners() {}

    open fun subscribeObservers() {

        viewModel.errorHandle.observe(this, EventObserver {
            it.message?.let { message ->
                "ErrorHandler".Logd(message)
                showMessage(message)
            }

        })


        viewModel.showLoading.observe(this, EventObserver {
            showLoading()
        })


        viewModel.hideLoading.observe(this, EventObserver {
            hideLoading()
        })

    }


    open fun showMessage(message: String) {
        baseActivity?.showMessage(message)
    }

    open fun showMessage(messageId: Int) {
        baseActivity?.showMessage(messageId)
    }

    open fun showLoading() {
        baseActivity?.showLoading()
    }

    open fun hideLoading() {
        baseActivity?.hideLoading()
    }

    override fun onDestroyView() {
        val parentViewGroup = view?.parent as ViewGroup?
        parentViewGroup?.removeAllViews()
        super.onDestroyView()
    }


}