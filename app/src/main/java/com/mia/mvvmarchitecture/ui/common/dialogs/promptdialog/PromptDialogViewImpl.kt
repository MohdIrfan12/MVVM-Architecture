package com.mia.mvvvmcarchitecture.ui.common.dialogs.promptdialog

import android.view.LayoutInflater
import com.mia.mvvmarchitecture.databinding.DialogPromptBinding
import com.mia.mvvvmcarchitecture.ui.common.views.BaseObservableView

/**
 * Created by Mohd Irfan
 * on 01/01/21.
 */
class PromptDialogViewImpl :
    BaseObservableView<DialogPromptBinding, PromptDialogView.Listener>,
    PromptDialogView {

    private var binding: DialogPromptBinding

    constructor(inflater: LayoutInflater) {
        binding = DialogPromptBinding.inflate(inflater);
        setViewBinding(binding)
        binding.btnNegative.setOnClickListener {
            for (listener in getListeners()) {
                listener.onNegativeButtonClicked()
            }
        }
        binding.btnPositive.setOnClickListener {
            for (listener in getListeners()) {
                listener.onPositiveButtonClicked()
            }
        }
    }

    override fun setTitle(title: String?) {
        binding.tvTitle.setText(title)
    }

    override fun setMessage(message: String?) {
        binding.tvMsg.setText(message)
    }

    override fun setPositiveButtonText(text: String?) {
        binding.btnPositive.setText(text)
    }

    override fun setNegativeButtonText(text: String?) {
        binding.btnNegative.setText(text)
    }
}