package com.ajaygalagali.quizly.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ajaygalagali.quizly.R
import com.ajaygalagali.quizly.databinding.FragmentQuestionDisplayBinding
import com.ajaygalagali.quizly.models.QuestionsModel
import com.ajaygalagali.quizly.models.QuizModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_question_display.*

private const val TAG = "QuestionDisplayFragment"
class QuestionDisplayFragment : Fragment(R.layout.fragment_question_display) {
    lateinit var binding: FragmentQuestionDisplayBinding
    private var counter = 0
    private var correctAnswerCounter = 0
    private var numberOfQuestions = HomeFragment.curQuizModel.questions.size
    private var chosenOption = "Z"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentQuestionDisplayBinding.bind(view)

        displayNextQuestion(counter)

        binding.btnNext.setOnClickListener {
            Log.d(TAG, "onViewCreated: Number of questions : $numberOfQuestions")
            cvOptionA.setCardBackgroundColor(Color.WHITE)
            cvOptionB.setCardBackgroundColor(Color.WHITE)
            cvOptionC.setCardBackgroundColor(Color.WHITE)
            cvOptionD.setCardBackgroundColor(Color.WHITE)
            if (chosenOption == HomeFragment.curQuizModel.questions[counter].correctOptionCode){
                correctAnswerCounter = correctAnswerCounter + 1
            }

            if (counter < numberOfQuestions -2){
                displayNextQuestion(++counter)
            }else if (counter == numberOfQuestions - 2){
                binding.btnNext.setText("Submit Madi")
                displayNextQuestion(++counter)
            }else{
                val action = QuestionDisplayFragmentDirections.actionQuestionDisplayFragmentToPlayEndFragment(correctAnswerCounter.toString(),numberOfQuestions)
                findNavController().navigate(action)
            }


        }

        binding.apply {

            cvOptionA.setOnClickListener {
                chosenOption = "A"
                cvOptionA.setCardBackgroundColor(Color.GREEN)
                cvOptionB.setCardBackgroundColor(Color.WHITE)
                cvOptionC.setCardBackgroundColor(Color.WHITE)
                cvOptionD.setCardBackgroundColor(Color.WHITE)
            }

            cvOptionB.setOnClickListener {
                chosenOption = "B"
                cvOptionA.setCardBackgroundColor(Color.WHITE)
                cvOptionB.setCardBackgroundColor(Color.GREEN)
                cvOptionC.setCardBackgroundColor(Color.WHITE)
                cvOptionD.setCardBackgroundColor(Color.WHITE)
            }

            cvOptionC.setOnClickListener {
                chosenOption = "C"
                cvOptionA.setCardBackgroundColor(Color.WHITE)
                cvOptionB.setCardBackgroundColor(Color.WHITE)
                cvOptionC.setCardBackgroundColor(Color.GREEN)
                cvOptionD.setCardBackgroundColor(Color.WHITE)
            }

            cvOptionD.setOnClickListener {
                chosenOption = "D"
                cvOptionA.setCardBackgroundColor(Color.WHITE)
                cvOptionB.setCardBackgroundColor(Color.WHITE)
                cvOptionC.setCardBackgroundColor(Color.WHITE)
                cvOptionD.setCardBackgroundColor(Color.GREEN)
            }

        }














    }

    private fun displayNextQuestion(position : Int){
        val questionArray : ArrayList<QuestionsModel> = HomeFragment.curQuizModel.questions
        binding.tvQDtext.text = questionArray[position].text
        binding.tvQDoptionA.text = questionArray[position].optionA
        binding.tvQDoptionB.text = questionArray[position].optionB
        binding.tvQDoptionC.text = questionArray[position].optionC
        binding.tvQDoptionD.text = questionArray[position].optionD

    }





}