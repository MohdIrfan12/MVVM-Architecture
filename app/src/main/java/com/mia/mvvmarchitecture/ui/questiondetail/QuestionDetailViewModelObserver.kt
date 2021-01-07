package com.mia.mvvmarchitecture.ui.questiondetail

 import com.mia.mvvvmcarchitecture.questions.QuestionDetail

/**
 * Created by Mohd Irfan
 * on 03/01/21.
 */
interface QuestionDetailViewModelObserver {
    fun onQuestionDetailFetchedEvent(questionDetail: QuestionDetail)
    fun onQuestionFetchFailedEvent()
    fun onShowProgressBarEvent()
    fun onHideProgressBarEvent()
}