package com.mia.mvvvmcarchitecture.common.dependencyInjection

import android.content.Context
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.mia.mvvmarchitecture.networking.EndpointFactory
import com.mia.mvvmarchitecture.questions.UsecaseFactory
import com.mia.mvvvmcarchitecture.common.eventbus.EventBus
import com.mia.mvvvmcarchitecture.common.permissions.FragmentPermissionsHelper
import com.mia.mvvvmcarchitecture.networking.StackoverflowApi
import com.mia.mvvvmcarchitecture.questions.FetchQuestionDetailUseCase
import com.mia.mvvvmcarchitecture.ui.common.ViewFactory
import com.mia.mvvvmcarchitecture.ui.common.controllers.BackPressDispatcher
import com.mia.mvvvmcarchitecture.ui.common.dialogs.DialogsManager
import com.mia.mvvvmcarchitecture.ui.common.screensnavigator.ScreenNavigatior
import com.mia.mvvvmcarchitecture.ui.common.toasthelper.ToastHelper
import com.mia.mvvmarchitecture.ui.common.fragmentframehelper.FragmentFrameHelper
import com.mia.mvvmarchitecture.ui.common.fragmentframehelper.FragmentFrameWrapper
import com.mia.mvvmarchitecture.ui.common.viewmodel.ViewModelProviderFactory
import com.mia.mvvvmcarchitecture.common.permissions.ActivityPermissionsHelper

/**
 * Created by Mohd Irfan on 31/12/20.
 *
 */
class ControllerCompositionRoot(private val mActivityCompositionRoot: ActivityCompositionRoot) {

    private val mActivityPermissionsHelper by lazy {
        ActivityPermissionsHelper(getActivity())
    }

    private var mFragmentPermissionsHelper: FragmentPermissionsHelper? = null

    fun getStackOverFlowApi(): StackoverflowApi {
        return mActivityCompositionRoot.getStackOverFlowApi()
    }

    private fun getContext(): Context {
        return mActivityCompositionRoot.getContext()
    }

    private fun getActivity(): FragmentActivity {
        return mActivityCompositionRoot.getActivity()
    }

    fun getEventBus(): EventBus {
        return mActivityCompositionRoot.getEventBus()
    }

    fun getActivityPermissionsHelper(): ActivityPermissionsHelper {
        return mActivityPermissionsHelper
    }

    private fun getFragmentManager(): FragmentManager {
        return getActivity().supportFragmentManager
    }

    private fun getLayoutInflator(): LayoutInflater {
        return LayoutInflater.from(getContext())
    }

    fun getViewFactory(): ViewFactory {
        return ViewFactory(getLayoutInflator())
    }

    fun getScreenNavigatior(): ScreenNavigatior {
        return ScreenNavigatior(getFragmentFrameHelper())
    }

    internal fun getMessageDisplayer(): ToastHelper {
        return ToastHelper(getContext())
    }

    fun getBackPressDispatcher(): BackPressDispatcher {
        return getActivity() as BackPressDispatcher
    }

    fun getDialogsManager(): DialogsManager {
        return DialogsManager(getContext(), getFragmentManager())
    }

    private fun getFragmentFrameHelper(): FragmentFrameHelper {
        return FragmentFrameHelper(getActivity(), getFragmentFrameWrapper(), getFragmentManager())
    }

    private fun getFragmentFrameWrapper(): FragmentFrameWrapper {
        return getActivity() as FragmentFrameWrapper
    }

    fun getFragmentPermissionsHelper(mFragment: Fragment): FragmentPermissionsHelper {
        if (mFragmentPermissionsHelper == null) {
            mFragmentPermissionsHelper =
                FragmentPermissionsHelper(mFragment)
        }
        return mFragmentPermissionsHelper!!
    }

    fun getEndpointFactory(): EndpointFactory {
        return EndpointFactory(getStackOverFlowApi())
    }

    fun getUsecaseFactory(): UsecaseFactory {
        return UsecaseFactory(getEndpointFactory())
    }

    fun getViewModelProviderFactory(): ViewModelProviderFactory {
        return ViewModelProviderFactory(this)
    }
}