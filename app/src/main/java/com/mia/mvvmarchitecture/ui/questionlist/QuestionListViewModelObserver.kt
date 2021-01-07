package com.mia.mvvmarchitecture.ui.questionlist

import com.mia.mvvvmcarchitecture.questions.Question

/**
 * Created by Mohd Irfan
 * on 03/01/21.
 */
interface QuestionListViewModelObserver {
    fun onQuestionFetchedEvent(questions: List<Question>?)
    fun onQuestionFetchFailedEvent()
    fun onShowProgressBarEvent()
    fun onHideProgressBarEvent()
}