package com.example.testinterview.domain.usecase

import com.example.testinterview.domain.QuestionRepository
import com.example.testinterview.domain.model.Question

class DeleteQuestionUseCase(
    private val questionRepository: QuestionRepository
) {
    operator fun invoke(question: Question) {
        questionRepository.deleteQuestion(question)
    }
}