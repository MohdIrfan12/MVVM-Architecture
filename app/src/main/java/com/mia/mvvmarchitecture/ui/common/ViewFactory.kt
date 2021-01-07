package com.mia.mvvvmcarchitecture.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import com.mia.mvvvmcarchitecture.ui.common.dialogs.promptdialog.PromptDialogView
import com.mia.mvvvmcarchitecture.ui.common.dialogs.promptdialog.PromptDialogViewImpl
import com.mia.mvvvmcarchitecture.ui.common.toolbar.ToolbarView
import com.mia.mvvvmcarchitecture.ui.questiondetail.QuestionDetailView
import com.mia.mvvvmcarchitecture.ui.questiondetail.QuestionDetailViewImpl
import com.mia.mvvvmcarchitecture.ui.questionlist.questionlistitem.QuestionsListItemView
import com.mia.mvvvmcarchitecture.ui.questionlist.questionlistitem.QuestionsListItemViewImpl
import com.mia.mvvvmcarchitecture.ui.questionlist.QuestionsListView
import com.mia.mvvvmcarchitecture.ui.questionlist.QuestionsListViewImpl

/**
 * Created by Mohd Irfan on 31/12/20.
 *
 */
class ViewFactory(private val mLayoutInflater: LayoutInflater) {

    fun getQuestionsListView(@Nullable parent: ViewGroup?): QuestionsListView {
        return QuestionsListViewImpl(mLayoutInflater, parent, this)
    }

    fun getQuestionsListItemView(@Nullable parent: ViewGroup): QuestionsListItemView {
        return QuestionsListItemViewImpl(mLayoutInflater, parent)
    }

    fun getQuestionDetailView(@Nullable parent: ViewGroup?): QuestionDetailView {
        return QuestionDetailViewImpl(mLayoutInflater, parent,this)
    }

    fun getToolbarView(@Nullable parent: ViewGroup?): ToolbarView {
        return ToolbarView(mLayoutInflater,parent)
    }

    fun getPromptDialogView(@Nullable parent: ViewGroup?): PromptDialogView {
        return PromptDialogViewImpl(mLayoutInflater)
    }
}