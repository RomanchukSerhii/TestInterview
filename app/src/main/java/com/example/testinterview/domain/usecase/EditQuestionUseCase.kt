package com.example.testinterview.domain.usecase

import com.example.testinterview.domain.QuestionRepository
import com.example.testinterview.domain.model.Question

class EditQuestionUseCase(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke(question: Question) {
        questionRepository.editQuestion(question)
    }
}