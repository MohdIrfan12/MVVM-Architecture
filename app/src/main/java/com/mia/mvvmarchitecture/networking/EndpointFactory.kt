package com.mia.mvvmarchitecture.networking

import com.mia.mvvmarchitecture.networking.question.FetchQuestionDetailEndpoint
import com.mia.mvvmarchitecture.networking.question.FetchQuestionListEndpoint
import com.mia.mvvvmcarchitecture.networking.StackoverflowApi

/**
 * Created by Mohd Irfan on 07/01/21.
 */
class EndpointFactory(private val mStackoverflowApi: StackoverflowApi) {

    fun getQuestionListEndpoint(): FetchQuestionListEndpoint {
        return FetchQuestionListEndpoint(mStackoverflowApi)
    }

    fun getQuestionDetailEndpoint(): FetchQuestionDetailEndpoint {
        return FetchQuestionDetailEndpoint(mStackoverflowApi)
    }
}