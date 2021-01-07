package com.mia.mvvvmcarchitecture.ui.questionlist

import androidx.lifecycle.ViewModel
import com.mia.mvvmarchitecture.ui.questionlist.QuestionListViewModelObserver
import com.mia.mvvvmcarchitecture.common.eventbus.BusDialogButtonClickEvent
import com.mia.mvvvmcarchitecture.common.eventbus.EventBus
import com.mia.mvvvmcarchitecture.questions.FetchQuestionsListUseCase
import com.mia.mvvvmcarchitecture.questions.Question

/**
 * Created by Mohd Irfan on 31/12/20.
 *
 */
class QuestionListViewModel(
    private val mFetchQuestionsListUseCase: FetchQuestionsListUseCase,
    private val mEventBus: EventBus
) : ViewModel(),
    FetchQuestionsListUseCase.Listener,
    EventBus.EventBusListener {

    internal enum class ScreenState {
        IDLE, QUESTION_DETAILS_SHOWN, NETWORK_ERROR
    }

    private var mScreenState: ScreenState? = ScreenState.IDLE
    private var mQuestionListViewModelObserver: QuestionListViewModelObserver? = null

    init {
        mFetchQuestionsListUseCase.addObserver(this)
    }

    internal fun attachObserver(questionListViewModelObserver: QuestionListViewModelObserver) {
        this.mQuestionListViewModelObserver = questionListViewModelObserver
        mEventBus.addObserver(this)
        if (mScreenState != ScreenState.NETWORK_ERROR) {
            fetchQuestions()
        }
    }

    internal fun detachObserver() {
        this.mQuestionListViewModelObserver = null
        mEventBus.removeObserver(this)
    }

    internal fun setScreenState(screenState: ScreenState?) {
        this.mScreenState = screenState
    }

    private fun fetchQuestions() {
        mQuestionListViewModelObserver?.onShowProgressBarEvent()
        mFetchQuestionsListUseCase.fetchQuestionListAndNotify()
    }


    override fun onQuestionsFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR
        mQuestionListViewModelObserver?.onHideProgressBarEvent();
        mQuestionListViewModelObserver?.onQuestionFetchFailedEvent();
    }

    override fun onQuestionsListFetched(questions: List<Question>) {
        mScreenState = ScreenState.QUESTION_DETAILS_SHOWN
        mQuestionListViewModelObserver?.onHideProgressBarEvent();
        mQuestionListViewModelObserver?.onQuestionFetchedEvent(questions)
    }

    override fun onEvent(event: Any) {
        mScreenState = ScreenState.IDLE
        when (event) {
            BusDialogButtonClickEvent.POSITIVE -> fetchQuestions()
            BusDialogButtonClickEvent.NEGATIVE -> {
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        detachObserver()
        mFetchQuestionsListUseCase.removeObserver(this)
    }

    internal fun getScreenState(): ScreenState? {
        return mScreenState
    }
}