package com.example.testinterview.domain.model

data class Question(
    val category: Category,
    val topic: Topic,
    val title: String,
    val answer: String,
    var shuffleMode: Boolean = SHUFFLE_MODE_OFF,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
        const val SHUFFLE_MODE_OFF = false
        const val SHUFFLE_MODE_ON = true
    }
}