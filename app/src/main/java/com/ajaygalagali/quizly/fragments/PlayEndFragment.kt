package com.ajaygalagali.quizly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ajaygalagali.quizly.R
import com.ajaygalagali.quizly.databinding.FragmentPlayEndBinding


class PlayEndFragment : Fragment(R.layout.fragment_play_end) {

    lateinit var binding: FragmentPlayEndBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPlayEndBinding.bind(view)

        val args : PlayEndFragmentArgs by navArgs()

        val finalScore = args.score
        val totaln = args.totalN

        binding.tvFinalScore.text = "Your final Score: " + finalScore + "/" + totaln




    }



}