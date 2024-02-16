package com.example.my_e_learning.data

data class QuizInformation(
    val id : Int,
    val question: String,
    val answer1: String,
    val answer2: String,
    val answer3: String,
    val answer4: String,
    val correctAnswer: String,
)
