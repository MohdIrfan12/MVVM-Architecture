package com.mia.mvvvmcarchitecture.ui.common.views

import android.content.Context
import androidx.annotation.StringRes
import androidx.viewbinding.ViewBinding

/**
 * Created by Mohd Irfan on 29/12/20.
 *
 */
abstract class BaseView<ViewBindingClassName : ViewBinding> : MvvmView<ViewBindingClassName> {
    private lateinit var mViewBinding: ViewBindingClassName

    protected fun setViewBinding(viewBinding: ViewBindingClassName) {
        this.mViewBinding = viewBinding;
    }

    override fun getViewBinding(): ViewBindingClassName {
        return mViewBinding
    }

    protected fun getContext(): Context {
        return mViewBinding?.root?.context
    }

    protected open fun getString(@StringRes id: Int): String {
        return getContext().getString(id)
    }
}