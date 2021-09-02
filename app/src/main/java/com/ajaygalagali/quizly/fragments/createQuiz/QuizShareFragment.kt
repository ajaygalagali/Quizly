package com.ajaygalagali.quizly.fragments.createQuiz

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ajaygalagali.quizly.R
import com.ajaygalagali.quizly.databinding.FragmentQuizShareBinding


class QuizShareFragment : Fragment(R.layout.fragment_quiz_share) {

    private lateinit var binding: FragmentQuizShareBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentQuizShareBinding.bind(view)

        val args : QuizShareFragmentArgs by navArgs()

        val curQuiz = args.quizModel

        binding.tvQuizCodeShare.text = curQuiz.quizCode


        binding.btnInviteFriends.setOnClickListener {

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, """
                    
                    Hey there! Join my quiz - ${curQuiz.title},
                    which is open between ${curQuiz.startTimestamp} and ${curQuiz.endTimestamp}.
                    Join using code - ${curQuiz.quizCode} on Quizly App.
                    
                """.trimIndent())
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }


    }


}