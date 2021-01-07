package com.mia.mvvvmcarchitecture.questions

import com.mia.mvvvmcarchitecture.common.observer.BaseObservable
import com.mia.mvvvmcarchitecture.networking.*
import com.mia.mvvvmcarchitecture.networking.question.QuestionSchema
import com.mia.mvvvmcarchitecture.networking.question.QuestionsListSchema
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


/**
 * Created by Mohd Irfan on 31/12/20.
 *
 */
class FetchQuestionsListUseCase(private val mStackoverflowApi: StackoverflowApi) :
    BaseObservable<FetchQuestionsListUseCase.FetchQuestionsListUseCaseListener>() {

    interface FetchQuestionsListUseCaseListener {
        fun onQuestionDetailFetchFailed()
        fun onQuestionsListFetched(questions: ArrayList<Question>)
    }

    fun fetchQuestionListAndNotify() {
        mStackoverflowApi.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
            ?.enqueue(object : Callback<QuestionsListSchema> {
                override fun onResponse(
                    call: Call<QuestionsListSchema>,
                    response: Response<QuestionsListSchema>
                ) {
                    if (response.isSuccessful) {
                        notifySucess(response.body()?.mQuestions)
                    } else {
                        notifyFailure()
                    }
                }

                override fun onFailure(call: Call<QuestionsListSchema>, t: Throwable) {
                    notifyFailure()
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
            listener.onQuestionDetailFetchFailed()
        }
    }
}