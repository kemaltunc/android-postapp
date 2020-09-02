package com.tunc.wallpaperandroid.presentation.auth.register

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns
import com.tunc.wallpaperandroid.R
import com.tunc.wallpaperandroid.core.BaseFragment
import com.tunc.wallpaperandroid.core.DataState
import com.tunc.wallpaperandroid.data.PermissionManager
import com.tunc.wallpaperandroid.databinding.FragmentRegisterBinding
import com.tunc.wallpaperandroid.domain.entity.UserEntity
import com.tunc.wallpaperandroid.presentation.tab.TabFragment
import com.tunc.wallpaperandroid.utility.FragmentController.Navigator
import com.tunc.wallpaperandroid.utility.LiveData.EventObserver
import com.tunc.wallpaperandroid.utility.enums.FormError
import com.tunc.wallpaperandroid.utility.extension.URItoFile
import com.tunc.wallpaperandroid.utility.extension.clickListener
import com.tunc.wallpaperandroid.utility.extension.loadFile
import com.yalantis.ucrop.UCrop
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import rx_activity_result2.RxActivityResult
import java.io.File
import java.util.*


@AndroidEntryPoint
@ExperimentalCoroutinesApi
class RegisterFragment : BaseFragment<RegisterViewModel, FragmentRegisterBinding>() {

    override val layoutRes: Int = R.layout.fragment_register
    override fun getViewModelClass() = RegisterViewModel::class.java


    private var imageFile: File? = null

    lateinit var rxPermissions: PermissionManager


    override fun define() {
        super.define()
        rxPermissions = PermissionManager(this)

    }

    override fun initUI() {
        super.initUI()

    }

    override fun subscribeObservers() {
        super.subscribeObservers()


        viewModel.userData.observe(viewLifecycleOwner, EventObserver { dataState ->
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
            when (state) {
                FormError.NOT_MATCH_PASSWORD -> {
                    showMessage(getString(R.string.password_dont_match))
                }
                FormError.NOTHING -> {

                }
                else -> {
                    showMessage(getString(R.string.cannot_be_black))
                }

            }
        })

    }

    override fun clickListeners() {
        binding.registerBtn.clickListener {
            viewModel.register(
                nameEd.text,
                surnameEd.text,
                emailEd.text,
                passwordEd.text,
                rePasswordEd.text,
                imageFile
            )
        }

        binding.userImg.clickListener {
            pickPhoto()
        }
    }

    @SuppressLint("CheckResult")
    private fun pickPhoto() {
        if (rxPermissions.checkGalleryPermissions()) {
            RxActivityResult.on(this).startIntent(PermissionManager.galleryIntent())
                .subscribe { result ->
                    if (result.resultCode() == RESULT_OK) {
                        try {
                            result.data().data?.let { uri ->
                                imageFile = URItoFile(requireActivity(), uri)

                                imageFile?.let { file ->
                                    binding.userImg.loadFile(file, true)
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


    private fun cropImage(sourceUri: Uri?) {
        if (sourceUri != null && activity != null) {
            val destinationUri = Uri.fromFile(
                File(
                    activity?.cacheDir,
                    queryName(activity?.contentResolver!!, sourceUri)
                )
            )
            UCrop.of(sourceUri, destinationUri).start(requireActivity())

        }


    }

    private fun queryName(resolver: ContentResolver, uri: Uri): String {
        val returnCursor = resolver.query(uri, null, null, null, null)!!
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        val name = returnCursor.getString(nameIndex)
        returnCursor.close()
        return name
    }

    private fun createUUID(): String {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase()
    }

    companion object {
        const val PERMISSION_GALLERY_CODE: Int = 1
        fun newInstance() = RegisterFragment()
    }

}