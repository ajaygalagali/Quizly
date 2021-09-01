package com.ajaygalagali.quizly.fragments.createQuiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajaygalagali.quizly.R
import com.ajaygalagali.quizly.adapters.QuestionsListAdapter
import com.ajaygalagali.quizly.databinding.FragmentQuestionsListBinding
import com.ajaygalagali.quizly.models.QuestionsModel


class QuestionsListFragment : Fragment(R.layout.fragment_questions_list) {
    private lateinit var binding : FragmentQuestionsListBinding
    lateinit var mAdapter : QuestionsListAdapter




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentQuestionsListBinding.bind(view)
        setupRecyclerView()

//        CreateQuizFragment.QuestionsArray.add(
//            QuestionsModel("id_1","Which is the capital city if India?","Blore","Mumbai","Delhi","Bijapur","C")
//        )
//        CreateQuizFragment.QuestionsArray.add(
//            QuestionsModel("id_1","Which is the capital city if England?","London","Mumbai","Delhi","Bijapur","C")
//        )
//        CreateQuizFragment.QuestionsArray.add(
//            QuestionsModel("id_1","Which is the capital city if USA?","Blore","Mumbai","Delhi","Washing Machine","C")
//        )


        binding.fabAddQuestion.setOnClickListener {


            val action = QuestionsListFragmentDirections.actionQuestionsListFragmentToAddEditQuestionFragment(QuestionsModel())
            findNavController().navigate(action)

        }


        mAdapter.setOnQuestionClicked {

            val action = QuestionsListFragmentDirections.actionQuestionsListFragmentToAddEditQuestionFragment(it)
            findNavController().navigate(action)
        }



    }

    private fun setupRecyclerView(){
        mAdapter = QuestionsListAdapter(CreateQuizFragment.QuestionsArray)

        binding.rvQuestionsList.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }







}
