package com.mia.mvvvmcarchitecture.ui.common.toasthelper

import android.content.Context
import android.widget.Toast
import com.mia.mvvmarchitecture.R

/**
 * Created by Mohd Irfan on 31/12/20.
 *
 */
class ToastHelper(private val mContext: Context) {

    fun showUsecaseError() {
        Toast.makeText(mContext, R.string.text_network_call_failed, Toast.LENGTH_SHORT).show()
    }

    fun showPremissionGrantedMsg() {
        Toast.makeText(mContext, R.string.text_permission_granted, Toast.LENGTH_SHORT).show()
    }

    fun showDeclinedMsg() {
        Toast.makeText(mContext, R.string.text_permission_declined, Toast.LENGTH_SHORT).show()
    }

    fun showPermissionDeclinedCantAskMoreMsg() {
        Toast.makeText(mContext, R.string.text_permission_declined_and_can_not_ask_more, Toast.LENGTH_SHORT).show()
    }
}