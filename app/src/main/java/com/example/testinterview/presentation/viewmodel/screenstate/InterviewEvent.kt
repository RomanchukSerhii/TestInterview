package com.example.testinterview.presentation.viewmodel.screenstate

import com.example.testinterview.domain.model.Question

sealed interface InterviewEvent {
    data class SortQuestions(val sortType: SortType) : InterviewEvent
    object ShowDialog : InterviewEvent
    object HideDialog : InterviewEvent
    data class DeleteQuestion(val question: Question) : InterviewEvent
    object ShowAnswer : InterviewEvent
    object HideAnswer : InterviewEvent
}