package com.ajaygalagali.quizly.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ajaygalagali.quizly.R
import com.ajaygalagali.quizly.databinding.FragmentHomeBinding
import com.ajaygalagali.quizly.models.QuizModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private const val TAG = "HomeFragment"
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    companion object{
        lateinit var curQuizModel : QuizModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)
        val db = Firebase.firestore



        binding.btnCreateQuiz.setOnClickListener {
            Toast.makeText(context,"Create Quiz",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_homeFragment_to_createQuizFragment2)

        }

        binding.btnPlayQuiz.setOnClickListener {
            Toast.makeText(context,"Play Quiz",Toast.LENGTH_SHORT).show()
            if (binding.etQuizCode.text?.isEmpty() == true){
                Snackbar.make(parentFragment?.view as View,"Enter Quiz code",
                    Snackbar.LENGTH_SHORT).show()
            }else{


                binding.progressBarPlay.visibility = View.VISIBLE


                db.collection("QuizCollection")
                    .document(binding.etQuizCode.text.toString()).get()
                    .addOnSuccessListener {doc ->

                        curQuizModel =   doc.toObject(QuizModel::class.java)!!
                        Log.d(TAG, "onViewCreated: QUiz model received from firestore")
                        findNavController().navigate(R.id.action_homeFragment_to_questionDisplayFragment)



                    }
                    .addOnFailureListener {
                        binding.progressBarPlay.visibility = View.GONE
                        Log.e(TAG, "onViewCreated: ${it.message}")
                        it.message?.let { it1 ->
                            Snackbar.make(parentFragment?.view as View, it1,
                                Snackbar.LENGTH_SHORT).show()
                        }
                    }

            }

        }



    }


}