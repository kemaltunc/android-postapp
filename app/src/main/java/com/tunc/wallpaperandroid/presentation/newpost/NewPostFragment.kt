package com.tunc.wallpaperandroid.presentation.newpost

import android.annotation.SuppressLint
import android.app.Activity
import com.tunc.wallpaperandroid.R
import com.tunc.wallpaperandroid.core.BaseFragment
import com.tunc.wallpaperandroid.core.DataState
import com.tunc.wallpaperandroid.data.PermissionManager
import com.tunc.wallpaperandroid.databinding.FragmentNewPostBinding
import com.tunc.wallpaperandroid.utility.FragmentController.Navigator
import com.tunc.wallpaperandroid.utility.LiveData.EventObserver
import com.tunc.wallpaperandroid.utility.enums.FormError
import com.tunc.wallpaperandroid.utility.extension.URItoFile
import com.tunc.wallpaperandroid.utility.extension.clickListener
import com.tunc.wallpaperandroid.utility.extension.loadFile
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import rx_activity_result2.RxActivityResult
import java.io.File

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class NewPostFragment : BaseFragment<NewPostViewModel, FragmentNewPostBinding>() {

    override val layoutRes: Int = R.layout.fragment_new_post

    lateinit var rxPermissions: PermissionManager

    override fun getViewModelClass() = NewPostViewModel::class.java

    private var imageFile: File? = null

    override fun define() {
        super.define()
        rxPermissions = PermissionManager(this)
    }

    override fun initUI() {
        super.initUI()
    }

    override fun subscribeObservers() {
        super.subscribeObservers()


        viewModel.formError.observe(viewLifecycleOwner, EventObserver {
            when (it) {
                FormError.MISSING_FILE -> {

                }
                FormError.NOTHING -> {

                }
                else -> {

                }
            }
        })


        viewModel.postData.observe(viewLifecycleOwner, EventObserver { dataState ->
            if (dataState is DataState.Success<Boolean>) {
                hideLoading()
                Navigator.navigateUp()
            }
        })
    }

    override fun clickListeners() {
        super.clickListeners()

        binding.selectPhotoButton.clickListener {
            pickPhoto()
        }

        binding.shareButton.setOnClickListener {
            viewModel.createPost(
                imageFile,
                binding.deviceName.text,
                binding.description.text.toString()
            )
        }

        binding.header.clickBackButton {
            Navigator.navigateUp()
        }
    }

    @SuppressLint("CheckResult")
    private fun pickPhoto() {

        if (rxPermissions.checkGalleryPermissions()) {
            RxActivityResult.on(this).startIntent(PermissionManager.galleryIntent())
                .subscribe { result ->
                    if (result.resultCode() == Activity.RESULT_OK) {
                        try {
                            result.data().data?.let { uri ->
                                imageFile = URItoFile(requireActivity(), uri)

                                imageFile?.let { file ->
                                    binding.selectedPhoto.loadFile(file)
                                }

                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                    }
                }
        } else {

            rxPermissions.getCameraPermission(granted = {
                pickPhoto()
            }, denied = {

            })

        }
    }

    companion object {
        fun newInstance() = NewPostFragment()
    }


}