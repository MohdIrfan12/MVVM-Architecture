package com.mia.mvvmarchitecture.questions

import com.mia.mvvmarchitecture.networking.EndpointFactory
import com.mia.mvvvmcarchitecture.questions.FetchQuestionDetailUseCase
import com.mia.mvvvmcarchitecture.questions.FetchQuestionsListUseCase

/**
 * Created by Mohd Irfan on 07/01/21.
 */
class UsecaseFactory(private val endpoint: EndpointFactory) {

    fun getFetchQuestionsListUseCase(): FetchQuestionsListUseCase {
        return FetchQuestionsListUseCase(endpoint.getQuestionListEndpoint())
    }

    fun getFetchQuestionsDetailUseCase(): FetchQuestionDetailUseCase {
        return FetchQuestionDetailUseCase(endpoint.getQuestionDetailEndpoint())
    }
}