package com.mia.mvvvmcarchitecture.networking

import com.mia.mvvvmcarchitecture.networking.question.QuestionDetailApiResponseSchema
import com.mia.mvvvmcarchitecture.networking.question.QuestionsListApiResponseSchema
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Mohd Irfan on 29/12/20.
 *
 */
interface StackoverflowApi {
    @GET("/questions?sort=activity&order=desc&site=stackoverflow&filter=withbody")
    fun fetchLastActiveQuestions(@Query("pagesize") pageSize: Int?): Call<QuestionsListApiResponseSchema>

    @GET("/questions/{questionId}?site=stackoverflow&filter=withbody")
    fun fetchQuestionDetails(@Path("questionId") questionId: String?): Call<QuestionDetailApiResponseSchema>
}