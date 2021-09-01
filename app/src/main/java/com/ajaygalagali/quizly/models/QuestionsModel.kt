package com.ajaygalagali.quizly.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class QuestionsModel(

    var id : String = UUID.randomUUID().toString(),
    var text : String = "",
    var optionA : String = "",
    var optionB : String = "",
    var optionC : String = "",
    var optionD : String = "",
    var correctOptionCode : String = ""

) : Parcelable
