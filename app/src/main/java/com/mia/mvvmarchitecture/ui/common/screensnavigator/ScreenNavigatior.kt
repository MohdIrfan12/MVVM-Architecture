package com.mia.mvvvmcarchitecture.ui.common.screensnavigator

import com.mia.mvvmarchitecture.ui.common.fragmentframehelper.FragmentFrameHelper
import com.mia.mvvvmcarchitecture.ui.questiondetail.QuestionDetailsFragment
import com.mia.mvvvmcarchitecture.ui.questionlist.QuestionsListFragment

/**
 * Created by Mohd Irfan on 31/12/20.
 *
 */
class ScreenNavigatior(private val mFragmentFrameHelper: FragmentFrameHelper) {

    fun navigateToQuestionList() {
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(QuestionsListFragment.newInstance())
    }

    fun navigateToQuestionDetail(mQuestionId: String?) {
        mFragmentFrameHelper.replaceFragment(QuestionDetailsFragment.newInstance(mQuestionId))
    }
}