package com.example.testinterview.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testinterview.domain.model.Question
import com.example.testinterview.domain.usecase.DeleteQuestionUseCase
import com.example.testinterview.domain.usecase.EditQuestionUseCase
import com.example.testinterview.domain.usecase.GetQuestionListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class InterviewViewModel @Inject constructor(
    private val getQuestionListUseCase: GetQuestionListUseCase,
    private val deleteQuestionUseCase: DeleteQuestionUseCase
) : ViewModel() {
    val questionListLD = getQuestionListUseCase.invoke()

    fun deleteQuestion(question: Question) {
        viewModelScope.launch {
            deleteQuestionUseCase.invoke(questionId = question.id)
        }
    }
}