package com.mia.mvvvmcarchitecture.ui.common.dialogs

import androidx.fragment.app.DialogFragment
import com.mia.mvvvmcarchitecture.common.CommonApplication
import com.mia.mvvvmcarchitecture.common.dependencyInjection.ActivityCompositionRoot
import com.mia.mvvvmcarchitecture.common.dependencyInjection.ControllerCompositionRoot

/**
 * Created by Mohd Irfan
 * on 01/01/21.
 */
abstract class BaseDialog : DialogFragment() {

    private val mActivityCompositionRoot by lazy {
        ActivityCompositionRoot(
            requireActivity(),
            (requireActivity().application as CommonApplication).getCompositionRoot()
        )
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