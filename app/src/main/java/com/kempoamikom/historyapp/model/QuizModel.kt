package com.kempoamikom.historyapp.model

data class QuizModel
    (
        val id: String,
        val question: String,
        val answer_true: String,
        val level: String,
        val type: String,
        val answer_one: String,
        val answer_two: String,
        val answer_three: String,
        val answer_four: String
)
