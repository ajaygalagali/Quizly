package com.ajaygalagali.quizly.fragments.createQuiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajaygalagali.quizly.R
import com.ajaygalagali.quizly.adapters.QuestionsListAdapter
import com.ajaygalagali.quizly.databinding.FragmentQuestionsListBinding


class QuestionsListFragment : Fragment(R.layout.fragment_questions_list) {
    lateinit var binding : FragmentQuestionsListBinding
    lateinit var mAdapter : QuestionsListAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentQuestionsListBinding.bind(view)



    }

    private fun setupRecyclerView(){
        mAdapter = QuestionsListAdapter()

        binding.rvQuestionsList.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}