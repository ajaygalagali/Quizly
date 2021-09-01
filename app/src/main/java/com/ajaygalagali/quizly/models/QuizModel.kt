package com.ajaygalagali.quizly.models

import java.util.*

data class QuizModel(
    var id : String = "",
    var title : String = "",
    var createdTimestamp : Date = Date(),
    var startTimestamp : Date = Date(),
    var endTimestamp: Date = Date(),
    var quizCode : String = "",
    var questions : QuestionsModel = QuestionsModel()
)
