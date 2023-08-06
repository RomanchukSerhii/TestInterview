package com.example.testinterview.presentation.viewmodel.screenstate

import android.text.BoringLayout
import com.example.testinterview.domain.model.Question

data class InterviewState (
    val questions: List<Question> = emptyList(),
    val title: String = "",
    val answer: String = "",
    val sortType: SortType = SortType.REGULAR,
    val isAnswerShown: Boolean = false,
    val isDeleteContact: Boolean = false
)