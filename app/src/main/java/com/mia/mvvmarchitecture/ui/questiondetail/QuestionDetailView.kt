package com.mia.mvvvmcarchitecture.ui.questiondetail

import com.mia.mvvmarchitecture.databinding.ActivityQuestionDetailBinding
import com.mia.mvvvmcarchitecture.questions.QuestionDetail
import com.mia.mvvvmcarchitecture.ui.common.views.ObservableView

/**
 * Created by Mohd Irfan on 31/12/20.
 *
 */
interface QuestionDetailView :
    ObservableView<ActivityQuestionDetailBinding, QuestionDetailView.Listener> {

    interface Listener {
        fun onNavigationUpClicked()
        fun onLocationRequestClicked()
    }

    fun bindQuestion(questionDetail: QuestionDetail?)
    fun showProgressBar()
    fun hideProgressBar()
}