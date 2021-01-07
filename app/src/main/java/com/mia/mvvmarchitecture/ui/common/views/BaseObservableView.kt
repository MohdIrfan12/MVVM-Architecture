package com.mia.mvvvmcarchitecture.ui.common.views

import androidx.viewbinding.ViewBinding
import java.util.*
import kotlin.collections.HashSet

/**
 * Created by Mohd Irfan on 29/12/20.
 *
 */
abstract class BaseObservableView<ViewBindingClassName : ViewBinding, ListenerType> :
    BaseView<ViewBindingClassName>(),
    ObservableView<ViewBindingClassName, ListenerType> {

    private val mListeners: HashSet<ListenerType> = HashSet(1)

    override fun registerLister(listener: ListenerType) {
        mListeners.add(listener)
    }

    override fun unregisterListener(listener: ListenerType) {
        mListeners.remove(listener)
    }

    protected fun getListeners(): Set<ListenerType> {
        return Collections.unmodifiableSet(mListeners)
    }
}