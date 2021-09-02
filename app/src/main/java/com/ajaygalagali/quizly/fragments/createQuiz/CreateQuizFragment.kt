package com.ajaygalagali.quizly.fragments.createQuiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ajaygalagali.quizly.R
import com.ajaygalagali.quizly.databinding.FragmentCreateQuizBinding
import com.ajaygalagali.quizly.models.QuestionsModel
import com.ajaygalagali.quizly.models.QuizModel
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.google.firebase.firestore.FirebaseFirestore
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random


class CreateQuizFragment : Fragment(R.layout.fragment_create_quiz){



    private val TAG = "CreateQuizFragment"

    private lateinit var binding: FragmentCreateQuizBinding

    companion object{
        val QuestionsArray : ArrayList<QuestionsModel> = arrayListOf()
    }

    private var startDateMilli = 0L
    private var startTimeMilli = 0L
    private var endDateMilli = 0L
    private var endTimeMilli = 0L




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = FragmentCreateQuizBinding.bind(view)

        val db = FirebaseFirestore.getInstance()

        binding.btnCreateQuizChild.setOnClickListener {

            binding.apply {

                val title = etQuizTitle.text.toString()
                val startDate = btnPickStartDate.text.toString()
                val endDate = btnPickEndDate.text.toString()
                val endTime = btnPickEndTime.text.toString()
                val startTime = btnPickStartTime.text.toString()

                when {
                    title.isEmpty() -> {
                        etQuizTitle.error = "Required"
                    }
                    startDate == "Pick Date" -> {
                        Snackbar.make(parentFragment?.view as View,"Pick start date",
                            Snackbar.LENGTH_SHORT).show()
                    }
                    startTime == "Pick Time" -> {
                        Snackbar.make(parentFragment?.view as View,"Pick start time",
                            Snackbar.LENGTH_SHORT).show()
                    }
                    endTime == "Pick Time" -> {
                        Snackbar.make(parentFragment?.view as View,"Pick end time",
                            Snackbar.LENGTH_SHORT).show()
                    }
                    endDate == "Pick Date" -> {
                        Snackbar.make(parentFragment?.view as View,"Pick end date",
                            Snackbar.LENGTH_SHORT).show()
                    }
                    QuestionsArray.isEmpty() ->{
                        Snackbar.make(parentFragment?.view as View,"Add Questions to Quiz",
                            Snackbar.LENGTH_SHORT).show()
                    }
                    else->{
                    Log.d(TAG, "onViewCreated: $title $startDate $startTime $endDate $endTime ")
                        // Upload to Firestore

                        val now = Calendar.getInstance().time


                        val newQuiz = QuizModel(
                            id = UUID.randomUUID().toString(),
                            title = title,
                            createdTimestamp = now,
                            startTimestamp = Date(startDateMilli+startTimeMilli),
                            endTimestamp = Date(endDateMilli+endTimeMilli),
                            quizCode = Random.nextInt(1000,9999).toString(),
                            questions = QuestionsArray


                        )
                        db.collection("QuizCollection").document(newQuiz.quizCode).set(newQuiz)

                            .addOnSuccessListener {
                            Toast.makeText(context, "Quiz created successfully - ${newQuiz.quizCode}",Toast.LENGTH_SHORT).show()
                                val action = CreateQuizFragmentDirections.actionCreateQuizFragment2ToQuizShareFragment(newQuiz)
                                findNavController().navigate(action)

                        }.addOnFailureListener {ex->
                            Log.d(TAG, "onViewCreated: ")
                            Snackbar.make(parentFragment?.view as View,ex.message!!,
                                Snackbar.LENGTH_SHORT).show()
                        }

                    }                    
                }


            }
            

        }

        // Go to QuestionsList Fragement
        binding.btnAddQuetions.setOnClickListener {
            findNavController().navigate(R.id.action_createQuizFragment2_to_questionsListFragment)
        }

        // Date and Time Picking
        binding.btnPickStartDate.setOnClickListener { pickStartDate() }
        binding.btnPickStartTime.setOnClickListener { pickStartTime() }
        binding.btnPickEndDate.setOnClickListener { pickEndDate() }
        binding.btnPickEndTime.setOnClickListener { pickEndTime() }


    }

    // Start Date Picker
    private fun pickStartDate() {


        val today = MaterialDatePicker.todayInUtcMilliseconds()
        val bounds = CalendarConstraints.Builder().setValidator(DateValidatorPointForward.now()).build()
        val mDatePicker = MaterialDatePicker.Builder.datePicker().apply {
            setTitleText("Date of starting")
            setSelection(today)
            setCalendarConstraints(bounds)
        }.build()

        mDatePicker.show(parentFragmentManager, "Date_picker")
        
        var dateLong = 0L

        mDatePicker.addOnPositiveButtonClickListener {
            var pickedStartDate = Date(it)
            startDateMilli = it - ((5 * 60 * 60 * 1000) + (30 * 60 * 1000))
            val df = DateFormat.getDateInstance(DateFormat.MEDIUM)
            binding.btnPickStartDate.text = df.format(pickedStartDate)


        }

    }

    // End Date Picker
    private fun pickEndDate() {


        val today = MaterialDatePicker.todayInUtcMilliseconds()
        val bounds = CalendarConstraints.Builder().setValidator(DateValidatorPointForward.now()).build()
        val mDatePicker = MaterialDatePicker.Builder.datePicker().apply {
            setTitleText("Date of end")
            setSelection(today)
            setCalendarConstraints(bounds)
        }.build()

        mDatePicker.show(parentFragmentManager, "Date_picker_end")

        var dateLong = 0L

        mDatePicker.addOnPositiveButtonClickListener {
            var pickedStartDate = Date(it)
            endDateMilli = it - ((5 * 60 * 60 * 1000) + (30 * 60 * 1000))
            val df = DateFormat.getDateInstance(DateFormat.MEDIUM)
            binding.btnPickEndDate.text = df.format(pickedStartDate)


        }

    }

    // Start Time Picker
    private fun pickStartTime(){

        val materialTimePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setTitleText("Time of starting")
            .build()

        materialTimePicker
            .addOnPositiveButtonClickListener {
                val hr = materialTimePicker.hour
                val min = materialTimePicker.minute
                startTimeMilli = ((hr*60*60*1000) + (min*60*1000)).toLong()

                var pickedStartTime = "${materialTimePicker.hour} : ${materialTimePicker.minute}"
                binding.btnPickStartTime.text = pickedStartTime



            }
        materialTimePicker.show(parentFragmentManager, "StartTimePicker")


    }

    // End Time Picker
    private fun pickEndTime(){

        val materialTimePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setTitleText("Time of End")
            .build()

        materialTimePicker
            .addOnPositiveButtonClickListener {
                val hr = materialTimePicker.hour
                val min = materialTimePicker.minute
                endTimeMilli = ((hr*60*60*1000) + (min*60*1000)).toLong()

                var pickedEndTime = "${materialTimePicker.hour} : ${materialTimePicker.minute}"
                binding.btnPickEndTime.text = pickedEndTime



            }
        materialTimePicker.show(parentFragmentManager, "StartTimePicker")


    }

}