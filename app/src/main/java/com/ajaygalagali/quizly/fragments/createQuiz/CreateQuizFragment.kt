package com.ajaygalagali.quizly.fragments.createQuiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ajaygalagali.quizly.R
import com.ajaygalagali.quizly.databinding.FragmentCreateQuizBinding
import com.ajaygalagali.quizly.models.QuestionsModel
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList


class CreateQuizFragment : Fragment(R.layout.fragment_create_quiz){



    private val TAG = "CreateQuizFragment"

    private lateinit var binding: FragmentCreateQuizBinding

    companion object{
        val QuestionsArray : ArrayList<QuestionsModel> = arrayListOf()
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = FragmentCreateQuizBinding.bind(view)

        activity?.actionBar?.title = "Create Quiz"

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
                    }else->{
                    Log.d(TAG, "onViewCreated: $title $startDate $startTime $endDate $endTime ")

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
            var startDateInMillisecond = it - ((5 * 60 * 60 * 1000) + (30 * 60 * 1000))
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
            var startDateInMillisecond = it - ((5 * 60 * 60 * 1000) + (30 * 60 * 1000))
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
//                timeInMillisecond = (hr*60*60*1000) + (min*60*1000)

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
//                timeInMillisecond = (hr*60*60*1000) + (min*60*1000)

                var pickedEndTime = "${materialTimePicker.hour} : ${materialTimePicker.minute}"
                binding.btnPickEndTime.text = pickedEndTime



            }
        materialTimePicker.show(parentFragmentManager, "StartTimePicker")


    }

}