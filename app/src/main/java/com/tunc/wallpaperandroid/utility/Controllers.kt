package com.tunc.wallpaperandroid.utility

import androidx.fragment.app.Fragment

object Controllers {
    const val MainController = "main_controller"
    const val TabController = "main_tab_controller"
}


val Fragment.MainController: String
    get() = Controllers.MainController
val Fragment.TabController: String
    get() = Controllers.TabController
