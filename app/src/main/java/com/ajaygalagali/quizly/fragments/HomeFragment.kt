package com.ajaygalagali.quizly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ajaygalagali.quizly.R
import com.ajaygalagali.quizly.databinding.FragmentHomeBinding
import com.google.firebase.firestore.FirebaseFirestore


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)
        var db = FirebaseFirestore.getInstance()




        binding.btnCreateQuiz.setOnClickListener {
            Toast.makeText(context,"Create Quiz",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_homeFragment_to_createQuizFragment2)

        }

        binding.btnPlayQuiz.setOnClickListener {
            Toast.makeText(context,"Play Quiz",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_homeFragment_to_questionDisplayFragment)

        }



    }


}