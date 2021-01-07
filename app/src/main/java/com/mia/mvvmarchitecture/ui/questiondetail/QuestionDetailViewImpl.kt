package com.mia.mvvvmcarchitecture.ui.questiondetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.appcompat.widget.Toolbar
import com.mia.mvvmarchitecture.R
import com.mia.mvvmarchitecture.databinding.ActivityQuestionDetailBinding
import com.mia.mvvvmcarchitecture.questions.QuestionDetail
import com.mia.mvvvmcarchitecture.ui.common.ViewFactory
import com.mia.mvvvmcarchitecture.ui.common.toolbar.ToolbarView
import com.mia.mvvvmcarchitecture.ui.common.views.BaseObservableView

/**
 * Created by Mohd Irfan on 31/12/20.
 *
 */
class QuestionDetailViewImpl :
    BaseObservableView<ActivityQuestionDetailBinding, QuestionDetailView.Listener>,
    QuestionDetailView, ToolbarView.NavigationUpClickListener,
    ToolbarView.LocationButtonClickListener {

    private lateinit var mToolbarView: ToolbarView
    private val mToolbar: Toolbar

    constructor(
        layoutInflater: LayoutInflater,
        @Nullable viewGroup: ViewGroup?,
        viewFactory: ViewFactory
    ) {

        setViewBinding(ActivityQuestionDetailBinding.inflate(layoutInflater, viewGroup, false))

        mToolbar = getViewBinding().root.findViewById(R.id.toolbar)
        initToolbarAndObserveEvents(viewFactory)
        mToolbar.addView(mToolbarView.getViewBinding().root)
    }

    private fun initToolbarAndObserveEvents(viewFactory: ViewFactory) {
        mToolbarView = viewFactory.getToolbarView(mToolbar)
        mToolbarView.setScreenTitle(getString(R.string.text_questions_detail))
        mToolbarView.enableNavigatioAndObserve(this)
        mToolbarView.enableLocationAndObserve(this)
    }

    override fun bindQuestion(questionDetail: QuestionDetail?) {
        getViewBinding().let {
            it.tvTitle.setText(questionDetail?.mTitle)
            it.tvBoody.setText(questionDetail?.mBody)
        }
    }

    override fun showProgressBar() {
        getViewBinding().progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        getViewBinding().progressBar.visibility = View.GONE
    }

    override fun onNavigationUpClicked() {
        for (listener in getListeners()) {
            listener.onNavigationUpClicked()
        }
    }

    override fun onLocationButtonClicked() {
        for (listener in getListeners()) {
            listener.onLocationRequestClicked()
        }
    }
}