package com.mia.mvvvmcarchitecture.common.dependencyInjection

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.mia.mvvvmcarchitecture.common.eventbus.EventBus
import com.mia.mvvvmcarchitecture.networking.StackoverflowApi

/**
 * Created by Mohd Irfan
 * on 01/01/21.
 */
class ActivityCompositionRoot(
    private val mActivity: FragmentActivity,
    private val mCompositionRoot: CompositionRoot
) {

    fun getStackOverFlowApi(): StackoverflowApi {
        return mCompositionRoot.getStackOverFlowApi()
    }

    fun getContext(): Context {
        return mActivity
    }

    fun getEventBus(): EventBus {
        return mCompositionRoot.getEventBus()
    }

    fun getActivity(): FragmentActivity {
        return mActivity
    }
}