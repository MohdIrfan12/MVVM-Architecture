package com.mia.mvvvmcarchitecture.ui.common.dialogs.promptdialog

import com.mia.mvvmarchitecture.databinding.DialogPromptBinding
import com.mia.mvvvmcarchitecture.ui.common.views.ObservableView

/**
 * Created by Mohd Irfan
 * on 01/01/21.
 */
interface PromptDialogView : ObservableView<DialogPromptBinding, PromptDialogView.Listener>{

    interface Listener {
        fun onPositiveButtonClicked()
        fun onNegativeButtonClicked()
    }

    fun setTitle(title: String?)
    fun setMessage(message: String?)
    fun setPositiveButtonText(text: String?)
    fun setNegativeButtonText(text: String?)
}