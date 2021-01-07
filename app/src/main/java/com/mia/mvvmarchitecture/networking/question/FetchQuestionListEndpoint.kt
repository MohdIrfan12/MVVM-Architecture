package com.mia.mvvmarchitecture.networking.question

import com.mia.mvvvmcarchitecture.networking.Constants
import com.mia.mvvvmcarchitecture.networking.StackoverflowApi
import com.mia.mvvvmcarchitecture.networking.question.QuestionSchema
import com.mia.mvvvmcarchitecture.networking.question.QuestionsListApiResponseSchema
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Mohd Irfan on 07/01/21.
 */
open class FetchQuestionListEndpoint(private val mStackoverflowApi: StackoverflowApi?) {

    interface Listener {
        fun onQuestionsFetchFailed()
        fun onQuestionsFetched(questions: List<QuestionSchema>?)
    }

    open fun fetchQuestions(listener: Listener) {
        mStackoverflowApi?.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
            ?.enqueue(object : Callback<QuestionsListApiResponseSchema> {
                override fun onResponse(
                    call: Call<QuestionsListApiResponseSchema>,
                    response: Response<QuestionsListApiResponseSchema>
                ) {
                    if (response.isSuccessful) {
                        listener.onQuestionsFetched(response.body()?.mQuestions)
                    } else {
                        listener.onQuestionsFetchFailed()
                    }
                }

                override fun onFailure(call: Call<QuestionsListApiResponseSchema>, t: Throwable) {
                    listener.onQuestionsFetchFailed()
                }
            })
    }
}