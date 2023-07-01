package com.example.testinterview.domain.usecase

import com.example.testinterview.domain.QuestionRepository
import javax.inject.Inject

class DeleteQuestionUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke(questionId: Int) {
        questionRepository.deleteQuestion(questionId)
    }
}