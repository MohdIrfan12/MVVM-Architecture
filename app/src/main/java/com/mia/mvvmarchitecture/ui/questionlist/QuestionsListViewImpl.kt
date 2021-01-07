package com.mia.mvvvmcarchitecture.ui.questionlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.mia.mvvmarchitecture.R
import com.mia.mvvmarchitecture.databinding.FragmentQuestionListBinding
import com.mia.mvvvmcarchitecture.questions.Question
import com.mia.mvvvmcarchitecture.ui.common.views.BaseObservableView
import com.mia.mvvvmcarchitecture.ui.common.ViewFactory
import com.mia.mvvvmcarchitecture.ui.common.toolbar.ToolbarView

/**
 * Created by Mohd Irfan on 29/12/20.
 *
 */
class QuestionsListViewImpl :
    BaseObservableView<FragmentQuestionListBinding, QuestionsListView.Listener>,
    QuestionsListView,
    QuestionsListAdapter.OnQuestionClickListener {

    private lateinit var mQuestionsListAdapter: QuestionsListAdapter
    private lateinit var mToolbarView: ToolbarView
    private val mToolbar: Toolbar

    constructor(
        inflater: LayoutInflater, @Nullable parent: ViewGroup?,
        viewFactory: ViewFactory
    ) {

        setViewBinding(FragmentQuestionListBinding.inflate(inflater, parent, false))
        initAndQuestionListAdapter(viewFactory)
        mToolbar = getViewBinding().root.findViewById(R.id.toolbar)
        setToolbar(viewFactory)
    }

    private fun initAndQuestionListAdapter(viewFactory: ViewFactory) {

        mQuestionsListAdapter = QuestionsListAdapter(viewFactory)
        mQuestionsListAdapter.setOnQuestionClickListener(this)

        val layoutManager = LinearLayoutManager(getContext())
        val mRecyclerView = getViewBinding().recyclerView;
        mRecyclerView.layoutManager = layoutManager
        mRecyclerView.adapter = mQuestionsListAdapter
    }

    private fun setToolbar(viewFactory: ViewFactory) {
        mToolbarView = viewFactory.getToolbarView(mToolbar)
        mToolbarView.setScreenTitle(getString(R.string.text_questions_list))
        mToolbar.addView(mToolbarView.getViewBinding().root)
    }

    override fun bindQuestions(questions: List<Question>?) {
        mQuestionsListAdapter?.setData(questions)
        mQuestionsListAdapter?.notifyDataSetChanged()
    }

    override fun showProgressBar() {
        getViewBinding().progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        getViewBinding().progressBar.visibility = View.GONE
    }

    override fun onQuestionClicked(question: Question?) {
        for (listener in getListeners()) {
            listener.onQuestionClicked(question)
        }
    }
}