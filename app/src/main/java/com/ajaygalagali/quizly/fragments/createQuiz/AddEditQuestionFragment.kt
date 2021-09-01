package com.ajaygalagali.quizly.fragments.createQuiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ajaygalagali.quizly.R
import com.ajaygalagali.quizly.databinding.FragmentAddEditQuestionBinding
import com.ajaygalagali.quizly.models.QuestionsModel
import com.google.android.material.snackbar.Snackbar
import java.util.*

private const val TAG = "AddEditQuestionFragment"
class AddEditQuestionFragment : Fragment(R.layout.fragment_add_edit_question) {

    lateinit var binding: FragmentAddEditQuestionBinding


    

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAddEditQuestionBinding.bind(view)

        val args :AddEditQuestionFragmentArgs by navArgs()

        val currentQuestion = args.questionArgs

        binding.apply {
            etQuestion.setText(currentQuestion.text)
            etOptionA.setText(currentQuestion.optionA)
            etOptionB.setText(currentQuestion.optionB)
            etOptionC.setText(currentQuestion.optionC)
            etOptionD.setText(currentQuestion.optionD)
            etCorrectOptionCode.setText(currentQuestion.correctOptionCode)
        }

        binding.btnAddNewQuestion.setOnClickListener {

            binding.apply {
                val question = etQuestion.text.toString()
                val optionA = etOptionA.text.toString()
                val optionB = etOptionB.text.toString()
                val optionC = etOptionC.text.toString()
                val optionD = etOptionD.text.toString()
                val correctOptionCode = etCorrectOptionCode.text.toString()

                when{
                    question.isEmpty() ->{
                        etQuestion.setError("Question cannot be blank!")
                    }
                    optionA.isEmpty() ->{
                        etOptionA.setError("Enter option A")
                    }
                    optionB.isEmpty() ->{
                        etOptionB.setError("Enter option B")
                    }
                    optionC.isEmpty() ->{
                        etOptionC.setError("Enter option C")
                    }
                    optionD.isEmpty() ->{
                        etOptionD.setError("Enter option D")
                    }
                    correctOptionCode.trim().isEmpty()->{
                        etCorrectOptionCode.error = "Which option is correct answer?"
                    }else->{
                        // Add Question to List!
                        val newUid = UUID.randomUUID().toString()
                        val newQuestion = QuestionsModel(
                            id = newUid,
                            text = question,
                            optionA = optionA,
                            optionB = optionB,
                            optionC = optionC,
                            optionD = optionD,
                            correctOptionCode = correctOptionCode
                        )

                        CreateQuizFragment.QuestionsArray.remove(currentQuestion)
                        CreateQuizFragment.QuestionsArray.add(newQuestion)

                        Toast.makeText(context,"Question Updated", Toast.LENGTH_SHORT).show()
                    
                        findNavController().popBackStack()

                    }
                }
            }

        }






    }



}