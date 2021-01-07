package com.mia.mvvvmcarchitecture.networking.question

import com.google.gson.annotations.SerializedName

/**
 * Created by Mohd Irfan on 29/12/20.
 *
 */
data class QuestionsListApiResponseSchema(
    @SerializedName("items")
    var mQuestions: List<QuestionSchema>? = null
)