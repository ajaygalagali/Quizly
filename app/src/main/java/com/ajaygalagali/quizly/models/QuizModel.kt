package com.ajaygalagali.quizly.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
data class QuizModel(
    var id : String = "",
    var title : String = "",
    var createdTimestamp : Date = Date(),
    var startTimestamp : Date = Date(),
    var endTimestamp: Date = Date(),
    var quizCode : String = "",
    var questions : ArrayList<QuestionsModel> = arrayListOf()
):Parcelable
