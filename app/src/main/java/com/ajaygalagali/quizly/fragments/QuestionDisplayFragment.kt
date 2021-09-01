package com.ajaygalagali.quizly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.ajaygalagali.quizly.R
import com.ajaygalagali.quizly.databinding.FragmentQuestionDisplayBinding


class QuestionDisplayFragment : Fragment(R.layout.fragment_question_display) {
    lateinit var binding: FragmentQuestionDisplayBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentQuestionDisplayBinding.bind(view)




    }





}