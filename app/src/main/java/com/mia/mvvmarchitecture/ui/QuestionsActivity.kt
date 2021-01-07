package com.mia.mvvvmcarchitecture.ui

import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.mia.mvvmarchitecture.databinding.ActivityQuestionsBinding
import com.mia.mvvmarchitecture.ui.common.fragmentframehelper.FragmentFrameWrapper
import com.mia.mvvvmcarchitecture.ui.common.controllers.BackPressDispatcher
import com.mia.mvvvmcarchitecture.ui.common.controllers.BackPressListener
import com.mia.mvvvmcarchitecture.ui.common.controllers.BaseActivity

class QuestionsActivity : BaseActivity(), BackPressDispatcher, FragmentFrameWrapper {

    private lateinit var mBinding: ActivityQuestionsBinding
    private val mBackPressDispatchers = HashSet<BackPressListener>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityQuestionsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        if (savedInstanceState == null) {
            getControllerCompositionRoot().getScreenNavigatior().navigateToQuestionList()
        }
    }

    override fun onBackPressed() {
        var isBackPressedConsumed = false;
        for (listener in mBackPressDispatchers) {
            if (listener.onBackPressed()) {
                isBackPressedConsumed = true
                break
            }
        }
        if (!isBackPressedConsumed) {
            super.onBackPressed()
        }
    }

    override fun registerListener(listener: BackPressListener) {
        mBackPressDispatchers.add(listener)
    }

    override fun unRegisterListener(listener: BackPressListener) {
        mBackPressDispatchers.remove(listener)
    }

    override fun onDispatchBackPressed() {
        onBackPressed()
    }

    override fun getFragmentFrame(): FrameLayout {
        return mBinding.frameContent
    }
}