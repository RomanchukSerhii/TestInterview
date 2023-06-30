package com.example.testinterview.domain.model

data class Question(
    val category: Category,
    val topic: String,
    val title: String,
    val answer: String,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        private const val UNDEFINED_ID = 0
    }
}