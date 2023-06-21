package com.example.testinterview.domain.usecase

import androidx.lifecycle.LiveData
import com.example.testinterview.domain.QuestionRepository
import com.example.testinterview.domain.model.Question

class GetQuestionUseCase (
    private val questionRepository: QuestionRepository
) {
    operator fun invoke(questionId: Int): LiveData<Question> {
        return questionRepository.getQuestion(questionId)
    }
}