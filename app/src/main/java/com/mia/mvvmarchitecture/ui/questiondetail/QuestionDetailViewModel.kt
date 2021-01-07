package com.mia.mvvvmcarchitecture.ui.questionlist


import androidx.lifecycle.ViewModel
import com.mia.mvvmarchitecture.ui.questiondetail.QuestionDetailViewModelObserver
import com.mia.mvvvmcarchitecture.questions.FetchQuestionDetailUseCase
import com.mia.mvvvmcarchitecture.questions.QuestionDetail
import com.mia.mvvvmcarchitecture.ui.common.controllers.BackPressDispatcher
import com.mia.mvvvmcarchitecture.ui.common.controllers.BackPressListener

/**
 * Created by Mohd Irfan on 31/12/20.
 *
 */
class QuestionDetailViewModel(
    private val mFetchQuestionDetailUseCase: FetchQuestionDetailUseCase,
    private var mBackPressDispatcher: BackPressDispatcher

) : ViewModel(),
    FetchQuestionDetailUseCase.Listener,
    BackPressListener {

    private var questioId: String? = null
    private var mObserver: QuestionDetailViewModelObserver? = null

    init {
        mFetchQuestionDetailUseCase.addObserver(this)
    }

    fun setQuestionId(questioId: String?) {
        this.questioId = questioId;
    }

    fun attachObserver(observer: QuestionDetailViewModelObserver?) {
        this.mObserver = observer;
        onStart()
    }

    fun removeObserver() {
        this.mObserver = null;
        onStop()
    }

    private fun onStart() {
        mBackPressDispatcher.registerListener(this)
        fetchQuestionDetail()
    }

    private fun onStop() {
        mBackPressDispatcher.unRegisterListener(this)
    }

    private fun fetchQuestionDetail() {
        mObserver?.onShowProgressBarEvent()
        mFetchQuestionDetailUseCase.fetchQuestionDetailAndNotify(questioId)
    }

    override fun onQuestionDetailFetched(questionDetail: QuestionDetail) {
        mObserver?.onHideProgressBarEvent()
        mObserver?.onQuestionDetailFetchedEvent(questionDetail)
    }

    override fun onQuestionDetailFetchFailed() {
        mObserver?.onHideProgressBarEvent()
        mObserver?.onQuestionFetchFailedEvent()
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    fun onDispatchBackPressed() {
        mBackPressDispatcher.onDispatchBackPressed()
    }

    override fun onCleared() {
        super.onCleared()
        onStop()
        mFetchQuestionDetailUseCase.removeObserver(this)
    }
}