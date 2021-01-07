package com.mia.mvvvmcarchitecture.ui.questionlist

 import com.mia.mvvmarchitecture.databinding.FragmentQuestionListBinding
 import com.mia.mvvvmcarchitecture.questions.Question
import com.mia.mvvvmcarchitecture.ui.common.views.ObservableView

/**
 * Created by Mohd Irfan on 29/12/20.
 *
 */
interface QuestionsListView :
    ObservableView<FragmentQuestionListBinding, QuestionsListView.Listener> {

    interface Listener {
        fun onQuestionClicked(question: Question?)
    }

    fun bindQuestions(questions: List<Question>?)
    fun showProgressBar()
    fun hideProgressBar()
}