package com.ajaygalagali.quizly.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajaygalagali.quizly.R
import com.ajaygalagali.quizly.databinding.FragmentQuestionsListBinding
import com.ajaygalagali.quizly.databinding.RowQuestionsListBinding
import com.ajaygalagali.quizly.models.QuestionsModel

class QuestionsListAdapter(var questionsArray : ArrayList<QuestionsModel>) : RecyclerView.Adapter<QuestionsListAdapter.QuestionsListViewHolder>() {

    inner class QuestionsListViewHolder(val binding : RowQuestionsListBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsListViewHolder {

        val binding = RowQuestionsListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return QuestionsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionsListViewHolder, position: Int) {
        val curItem = questionsArray[position]

        holder.binding.tvQuestionOnList.text = (position+1).toString() + ": "+curItem.text

        holder.binding.tvQuestionOnList.setOnClickListener {
            onQuestionClicked?.let { it(curItem) }
        }



    }

    override fun getItemCount(): Int {
        return questionsArray.size
    }

    // Join click
    private var onQuestionClicked:((QuestionsModel)->Unit)? = null

    fun setOnQuestionClicked(listener:(QuestionsModel) -> Unit){
        onQuestionClicked = listener
    }


}