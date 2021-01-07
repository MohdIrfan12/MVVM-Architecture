package com.mia.mvvvmcarchitecture.ui.questionlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModelProvider
import com.mia.mvvmarchitecture.ui.questionlist.QuestionListViewModelObserver
import com.mia.mvvvmcarchitecture.questions.Question
import com.mia.mvvvmcarchitecture.ui.common.controllers.BaseFragment
import com.mia.mvvvmcarchitecture.ui.common.dialogs.DialogsManager
import com.mia.mvvvmcarchitecture.ui.common.screensnavigator.ScreenNavigatior

/**
 * Created by Mohd Irfan
 * on 01/01/21.
 */
class QuestionsListFragment : BaseFragment(), QuestionsListView.Listener,
    QuestionListViewModelObserver {

    companion object {
        private const val SAVED_STATE_SCREEN_STATE = "SAVED_STATE_SCREEN_STATE"
        fun newInstance(): QuestionsListFragment {
            val fragment = QuestionsListFragment();
            return fragment;
        }
    }

    private lateinit var mScreenNavigatior: ScreenNavigatior
    private lateinit var mQuestionListViewModel: QuestionListViewModel
    private lateinit var mDialogsManager: DialogsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mQuestionListViewModel = ViewModelProvider(
            this, getControllerCompositionRoot().getViewModelProviderFactory()
        ).get(QuestionListViewModel::class.java)

        mScreenNavigatior = getControllerCompositionRoot().getScreenNavigatior()
        mDialogsManager = getControllerCompositionRoot().getDialogsManager()
        mQuestionListViewModel.setScreenState(
            savedInstanceState?.getSerializable(
                SAVED_STATE_SCREEN_STATE
            ) as QuestionListViewModel.ScreenState?
        )
    }

    private lateinit var mQuestionsListView: QuestionsListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mQuestionsListView =
            getControllerCompositionRoot().getViewFactory().getQuestionsListView(null)
        return mQuestionsListView.getViewBinding().root
    }

    override fun onSaveInstanceState(@NonNull outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(SAVED_STATE_SCREEN_STATE, mQuestionListViewModel.getScreenState())
    }

    override fun onStart() {
        super.onStart()
        mQuestionsListView.registerLister(this)
        mQuestionListViewModel.attachObserver(this)
    }

    override fun onStop() {
        super.onStop()
        mQuestionsListView.registerLister(this)
        mQuestionListViewModel.detachObserver()
    }

    override fun onQuestionClicked(question: Question?) {
        mScreenNavigatior?.navigateToQuestionDetail(question?.mId)
    }

    override fun onQuestionFetchedEvent(questions: List<Question>?) {
        mQuestionsListView.bindQuestions(questions)
    }

    override fun onQuestionFetchFailedEvent() {
        mDialogsManager.showErrorPrompt(null)
    }

    override fun onShowProgressBarEvent() {
        mQuestionsListView.showProgressBar()
    }

    override fun onHideProgressBarEvent() {
        mQuestionsListView.hideProgressBar()
    }
}