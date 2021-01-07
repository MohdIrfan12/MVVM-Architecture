package com.mia.mvvvmcarchitecture.ui.questionlist.questionlistitem

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.mia.mvvmarchitecture.databinding.ItemViewQuestionsListBinding
import com.mia.mvvvmcarchitecture.questions.Question
import com.mia.mvvvmcarchitecture.ui.common.views.BaseObservableView

/**
 * Created by Mohd Irfan on 29/12/20.
 *
 */
class QuestionsListItemViewImpl :
    BaseObservableView<ItemViewQuestionsListBinding, QuestionsListItemView.Listener>,
    QuestionsListItemView {

    private var mQuestion: Question? = null
    private var tvTitle: TextView

    constructor(layoutInflater: LayoutInflater, viewGroup: ViewGroup) {
        setViewBinding(ItemViewQuestionsListBinding.inflate(layoutInflater, viewGroup, false))
        tvTitle = getViewBinding()?.txtTitle;
        setItemClick()
    }

    private fun setItemClick() {
        getViewBinding()?.root?.setOnClickListener {
            for (listener in getListeners()) {
                listener.onQuestionClicked(mQuestion);
            }
        }
    }

    override fun bindQuestion(questions: Question?) {
        this.mQuestion = questions;
        tvTitle.setText(questions?.mTitle ?: "")
    }
}