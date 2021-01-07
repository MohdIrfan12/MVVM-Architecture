package com.mia.mvvvmcarchitecture.ui.questionlist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mia.mvvvmcarchitecture.questions.Question
import com.mia.mvvvmcarchitecture.ui.common.ViewFactory
import com.mia.mvvvmcarchitecture.ui.questionlist.questionlistitem.QuestionsListItemView

/**
 * Created by Mohd Irfan on 29/12/20.
 *
 */
class QuestionsListAdapter constructor(private val mViewFactory: ViewFactory) :
    RecyclerView.Adapter<QuestionsListAdapter.ViewHolder>(),
    QuestionsListItemView.Listener {

    private var dataList: List<Question>? = null
    private var mClickListener: OnQuestionClickListener? = null;

    fun setData(list: List<Question>?) {
        this.dataList = list;
    }

    fun setOnQuestionClickListener(listener: OnQuestionClickListener) {
        this.mClickListener = listener;
    }

    override fun getItemCount(): Int {
        return if (dataList != null) dataList!!.size else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mQuestionsListItemView = mViewFactory.getQuestionsListItemView(parent)
        return ViewHolder(mQuestionsListItemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = dataList?.get(position)
        holder.mQuestionsListItemView.bindQuestion(question)
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.mQuestionsListItemView.registerLister(this)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.mQuestionsListItemView.unregisterListener(this)
    }

    class ViewHolder(val mQuestionsListItemView: QuestionsListItemView) :
        RecyclerView.ViewHolder(mQuestionsListItemView.getViewBinding()?.root)

    interface OnQuestionClickListener {
        fun onQuestionClicked(question: Question?)
    }

    override fun onQuestionClicked(question: Question?) {
        mClickListener?.onQuestionClicked(question)
    }
}