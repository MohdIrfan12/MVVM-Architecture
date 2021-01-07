package com.mia.mvvvmcarchitecture.questions

import com.mia.mvvvmcarchitecture.common.observer.BaseObservable
import com.mia.mvvvmcarchitecture.networking.question.QuestionDetailSchema
import com.mia.mvvvmcarchitecture.networking.question.QuestionSchema
import com.mia.mvvvmcarchitecture.networking.StackoverflowApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Mohd Irfan on 31/12/20.
 *
 */
class FetchQuestionDetailUseCase(private val mStackoverflowApi: StackoverflowApi) :
    BaseObservable<FetchQuestionDetailUseCase.FetchQuestionDetailUseCaseListener>() {

    interface FetchQuestionDetailUseCaseListener {
        fun onQuestionDetailFetched(questionDetail: QuestionDetail)
        fun onQuestionDetailFetchFailed()
    }

    fun fetchQuestionDetailAndNotify(questionId: String?) {
        mStackoverflowApi.fetchQuestionDetails(questionId)
            .enqueue(object : Callback<QuestionDetailSchema> {
                override fun onResponse(
                    call: Call<QuestionDetailSchema>,
                    response: Response<QuestionDetailSchema>
                ) {
                    if (response.isSuccessful) {
                        notifySucess(response.body()?.getQuestion())
                    } else {
                        notifyFailure()
                    }
                }

                override fun onFailure(call: Call<QuestionDetailSchema>, t: Throwable) {
                    notifyFailure()
                }
            })
    }

    private fun notifySucess(mQuestionSchema: QuestionSchema?) {
        val questionDetail = QuestionDetail()
        mQuestionSchema?.let {
            questionDetail.mId = it.mId;
            questionDetail.mTitle = it.mTitle;
            questionDetail.mBody = it.mBody;
        }
        for (listener in getObserver()) {
            listener.onQuestionDetailFetched(questionDetail)
        }
    }

    private fun notifyFailure() {
        for (listener in getObserver()) {
            listener.onQuestionDetailFetchFailed()
        }
    }
}