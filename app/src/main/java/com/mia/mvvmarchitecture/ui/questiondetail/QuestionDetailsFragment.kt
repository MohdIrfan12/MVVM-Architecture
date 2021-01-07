package com.mia.mvvvmcarchitecture.ui.questiondetail

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mia.mvvmarchitecture.ui.questiondetail.QuestionDetailViewModelObserver
import com.mia.mvvvmcarchitecture.common.permissions.FragmentPermissionsHelper
import com.mia.mvvvmcarchitecture.questions.QuestionDetail
import com.mia.mvvvmcarchitecture.ui.common.controllers.BaseFragment
import com.mia.mvvvmcarchitecture.ui.common.toasthelper.ToastHelper
import com.mia.mvvvmcarchitecture.ui.questionlist.QuestionDetailViewModel

class QuestionDetailsFragment : BaseFragment(),
    QuestionDetailView.Listener,
    FragmentPermissionsHelper.Listener,
    QuestionDetailViewModelObserver {

    companion object {

        private const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"
        private const val REQUEST_CODE = 1001

        fun newInstance(questionId: String?): QuestionDetailsFragment {
            val fragment = QuestionDetailsFragment()
            val argument = Bundle()
            argument.putString(EXTRA_QUESTION_ID, questionId)
            fragment.arguments = argument;
            return fragment
        }
    }

    private lateinit var mToastHelper: ToastHelper
    private lateinit var mView: QuestionDetailView
    private lateinit var mPermissionsHelper: FragmentPermissionsHelper
    private lateinit var mQuestionDetailViewModel: QuestionDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mToastHelper = getControllerCompositionRoot().getMessageDisplayer()
        mPermissionsHelper = getControllerCompositionRoot().getFragmentPermissionsHelper(this)

        mQuestionDetailViewModel = ViewModelProvider(
            this, getControllerCompositionRoot().getViewModelProviderFactory()
        ).get(QuestionDetailViewModel::class.java)
        mQuestionDetailViewModel.setQuestionId(arguments?.getString(EXTRA_QUESTION_ID))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = getControllerCompositionRoot().getViewFactory().getQuestionDetailView(null)
        return mView.getViewBinding().root
    }

    override fun onStart() {
        super.onStart()
        mView.registerLister(this)
        mPermissionsHelper.addObserver(this)
        mQuestionDetailViewModel.attachObserver(this)
    }

    override fun onStop() {
        super.onStop()
        mView.unregisterListener(this)
        mPermissionsHelper.removeObserver(this)
        mQuestionDetailViewModel.removeObserver()
    }

    override fun onQuestionDetailFetchedEvent(questionDetail: QuestionDetail) {
        mView.bindQuestion(questionDetail)
    }

    override fun onQuestionFetchFailedEvent() {
        mToastHelper.showUsecaseError()
    }

    override fun onShowProgressBarEvent() {
        mView.showProgressBar()
    }

    override fun onHideProgressBarEvent() {
        mView.hideProgressBar()
    }

    override fun onNavigationUpClicked() {
        mQuestionDetailViewModel.onDispatchBackPressed()
    }

    override fun onPermissionGranted(permission: String, requestCode: Int) {
        mToastHelper.showPremissionGrantedMsg()
    }

    override fun onPermissionDeclined(permission: String, requestCode: Int) {
        mToastHelper.showDeclinedMsg()
    }

    override fun onPermissionDeclinedDontAskAgain(permission: String, requestCode: Int) {
        mToastHelper.showPermissionDeclinedCantAskMoreMsg()
    }

    override fun onLocationRequestClicked() {
        mPermissionsHelper.checkAndRequestPremission(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        mPermissionsHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}