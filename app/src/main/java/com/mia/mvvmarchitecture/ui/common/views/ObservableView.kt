package com.mia.mvvvmcarchitecture.ui.common.views

import androidx.viewbinding.ViewBinding

/**
 * Created by Mohd Irfan on 29/12/20.
 *
 */
interface ObservableView<ViewBindingClassName : ViewBinding, ListenerType> :
    MvvmView<ViewBindingClassName> {

    fun registerLister(listener: ListenerType)

    fun unregisterListener(listener: ListenerType)
}