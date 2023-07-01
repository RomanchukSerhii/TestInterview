package com.example.testinterview.domain.usecase

import com.example.testinterview.domain.QuestionRepository
import com.example.testinterview.domain.model.Question
import javax.inject.Inject

class GetQuestionUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke(questionId: Int): Question {
        return questionRepository.getQuestion(questionId)
    }
}