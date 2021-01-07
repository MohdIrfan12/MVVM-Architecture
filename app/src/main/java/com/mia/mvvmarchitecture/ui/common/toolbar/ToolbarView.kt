package com.mia.mvvvmcarchitecture.ui.common.toolbar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import com.mia.mvvmarchitecture.databinding.LayoutToolbarBinding
import com.mia.mvvvmcarchitecture.ui.common.views.BaseView

/**
 * Created by Mohd Irfan on 31/12/20.
 *
 */
class ToolbarView :
    BaseView<LayoutToolbarBinding> {

    interface NavigationUpClickListener {
        fun onNavigationUpClicked()
    }

    interface LocationButtonClickListener {
        fun onLocationButtonClicked()
    }

    constructor(inflater: LayoutInflater, @Nullable parent: ViewGroup?) {
        setViewBinding(LayoutToolbarBinding.inflate(inflater, parent, false))
    }

    fun setScreenTitle(titleText: String) {
        getViewBinding().tvToolbarTitle.setText(titleText)
    }

    fun enableNavigatioAndObserve(listener: NavigationUpClickListener) {
        getViewBinding().btnBack.visibility = View.VISIBLE
        getViewBinding().btnBack.setOnClickListener {
            listener.onNavigationUpClicked()
        }
    }

    fun enableLocationAndObserve(listener: LocationButtonClickListener) {
        getViewBinding().ivstorage.visibility = View.VISIBLE
        getViewBinding().ivstorage.setOnClickListener {
            listener.onLocationButtonClicked()
        }
    }
}