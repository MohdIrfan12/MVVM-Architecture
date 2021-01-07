package com.mia.mvvvmcarchitecture.ui.questionlist.questionlistitem

import com.mia.mvvmarchitecture.databinding.ItemViewQuestionsListBinding
import com.mia.mvvvmcarchitecture.questions.Question
import com.mia.mvvvmcarchitecture.ui.common.views.ObservableView

/**
 * Created by Mohd Irfan on 29/12/20.
 *
 */
interface QuestionsListItemView :
    ObservableView<ItemViewQuestionsListBinding, QuestionsListItemView.Listener> {

    interface Listener {
        fun onQuestionClicked(question: Question?)
    }

    fun bindQuestion(questions: Question?)
}