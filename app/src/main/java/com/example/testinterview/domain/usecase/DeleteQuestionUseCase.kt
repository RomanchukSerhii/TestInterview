package com.example.testinterview.domain.usecase

import com.example.testinterview.domain.QuestionRepository

class DeleteQuestionUseCase(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke(questionId: Int) {
        questionRepository.deleteQuestion(questionId)
    }
}