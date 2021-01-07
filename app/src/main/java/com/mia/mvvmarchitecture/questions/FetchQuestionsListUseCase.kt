package com.mia.mvvvmcarchitecture.questions

import com.mia.mvvmarchitecture.networking.question.FetchQuestionListEndpoint
import com.mia.mvvvmcarchitecture.common.observer.BaseObservable
import com.mia.mvvvmcarchitecture.networking.question.QuestionSchema
import java.util.ArrayList


/**
 * Created by Mohd Irfan on 31/12/20.
 *
 */
class FetchQuestionsListUseCase(private val endpoint: FetchQuestionListEndpoint) :
    BaseObservable<FetchQuestionsListUseCase.Listener>() {

    interface Listener {
        fun onQuestionsFetchFailed()
        fun onQuestionsListFetched(questions: List<Question>)
    }

    fun fetchQuestionListAndNotify() {
        endpoint.fetchQuestions(object : FetchQuestionListEndpoint.Listener {
            override fun onQuestionsFetchFailed() {
                notifyFailure()
            }

            override fun onQuestionsFetched(questions: List<QuestionSchema>?) {
                notifySucess(questions)
            }
        })
    }

    private fun notifySucess(mQuestionSchemas: List<QuestionSchema>?) {
        val questions: ArrayList<Question> = ArrayList<Question>()
        if (mQuestionSchemas != null) {
            for (item in mQuestionSchemas) {
                questions.add(Question(item.mId, item.mTitle))
            }
        }
        for (listener in getObserver()) {
            listener.onQuestionsListFetched(questions)
        }
    }

    private fun notifyFailure() {
        for (listener in getObserver()) {
            listener.onQuestionsFetchFailed()
        }
    }
}