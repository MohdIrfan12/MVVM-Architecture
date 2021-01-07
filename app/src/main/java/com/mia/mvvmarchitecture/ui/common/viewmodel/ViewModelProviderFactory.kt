package com.mia.mvvmarchitecture.ui.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.mia.mvvvmcarchitecture.common.dependencyInjection.ControllerCompositionRoot
import com.mia.mvvvmcarchitecture.ui.questionlist.QuestionDetailViewModel
import com.mia.mvvvmcarchitecture.ui.questionlist.QuestionListViewModel

/**
 * Created by Mohd Irfan on 31/12/20.
 */
class ViewModelProviderFactory : NewInstanceFactory {

    private val mControllerCompositionRoot: ControllerCompositionRoot

    constructor(controllerCompositionRoot: ControllerCompositionRoot) {
        this.mControllerCompositionRoot = controllerCompositionRoot;
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(QuestionListViewModel::class.java)) {
            return QuestionListViewModel(
                mControllerCompositionRoot.getUsecaseFactory().getFetchQuestionsListUseCase(),
                mControllerCompositionRoot.getEventBus()
            ) as T

        } else if (modelClass.isAssignableFrom(QuestionDetailViewModel::class.java)) {
            return QuestionDetailViewModel(
                mControllerCompositionRoot.getUsecaseFactory().getFetchQuestionsDetailUseCase(),
                mControllerCompositionRoot.getBackPressDispatcher()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}