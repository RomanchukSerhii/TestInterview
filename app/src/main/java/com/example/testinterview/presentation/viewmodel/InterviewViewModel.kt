package com.example.testinterview.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.testinterview.domain.usecase.GetQuestionListUseCase
import javax.inject.Inject

class InterviewViewModel @Inject constructor(
    private val getQuestionListUseCase: GetQuestionListUseCase
) : ViewModel() {
    val questionListLD = getQuestionListUseCase.invoke()
}