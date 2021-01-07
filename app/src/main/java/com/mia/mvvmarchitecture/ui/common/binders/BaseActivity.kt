package com.mia.mvvvmcarchitecture.ui.common.controllers

import androidx.appcompat.app.AppCompatActivity
import com.mia.mvvvmcarchitecture.common.CommonApplication
import com.mia.mvvvmcarchitecture.common.dependencyInjection.ActivityCompositionRoot
import com.mia.mvvvmcarchitecture.common.dependencyInjection.ControllerCompositionRoot

abstract class BaseActivity : AppCompatActivity() {

    private val mActivityCompositionRoot by lazy {
        ActivityCompositionRoot(this, (application as CommonApplication).getCompositionRoot())
    }

    private val mControllerCompositionRoot by lazy {
        ControllerCompositionRoot(getActivityCompositionRoot())
    }

    protected fun getActivityCompositionRoot(): ActivityCompositionRoot {
        return mActivityCompositionRoot
    }

    protected fun getControllerCompositionRoot(): ControllerCompositionRoot {
        return mControllerCompositionRoot
    }
}