package com.example.testinterview.domain.model

data class Question(
    val id: Int,
    val category: String,
    val topic: String,
    val title: String,
    val answer: String
)