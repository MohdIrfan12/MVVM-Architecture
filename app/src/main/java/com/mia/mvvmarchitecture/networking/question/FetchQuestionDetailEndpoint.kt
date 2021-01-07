package com.mia.mvvmarchitecture.networking.question

import com.mia.mvvvmcarchitecture.networking.StackoverflowApi
import com.mia.mvvvmcarchitecture.networking.question.QuestionDetailApiResponseSchema
import com.mia.mvvvmcarchitecture.networking.question.QuestionSchema
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Mohd Irfan on 07/01/21.
 */
open class FetchQuestionDetailEndpoint(private val mStackoverflowApi: StackoverflowApi?) {

    interface Listener {
        fun onQuestionDetailFetched(questionDetail: QuestionSchema?)
        fun onQuestionDetailFetchFailed()
    }

    open fun fetchQuestions(questionId: String?, listener: Listener) {
        mStackoverflowApi?.fetchQuestionDetails(questionId)?.enqueue(object : Callback<QuestionDetailApiResponseSchema> {
                override fun onResponse(
                    call: Call<QuestionDetailApiResponseSchema>,
                    response: Response<QuestionDetailApiResponseSchema>
                ) {
                    if (response.isSuccessful) {
                        listener.onQuestionDetailFetched(response.body()?.getQuestion())
                    } else {
                        listener.onQuestionDetailFetchFailed()
                    }
                }

                override fun onFailure(call: Call<QuestionDetailApiResponseSchema>, t: Throwable) {
                    listener.onQuestionDetailFetchFailed()
                }
            })
    }
}