package com.example.testinterview.domain.model

data class Topic(
    val category: Category,
    val questions: List<Question>,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        private const val UNDEFINED_ID = 0
    }
}
