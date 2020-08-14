package com.kempoamikom.historyapp.entity

data class QuizEntity (
    var idQuiz: Int = 0,
    var questionNumber: Int = 1,
    var trueAnswer: String = "",
    var answerOne: String = "",
    var answerTwo: String = "",
    var answerThree: String = "",
    var answerFour: String = "",
    var level: String = "",
    var question: String = ""
)