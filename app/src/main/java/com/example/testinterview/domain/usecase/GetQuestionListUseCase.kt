package com.example.testinterview.domain.usecase

import androidx.lifecycle.LiveData
import com.example.testinterview.domain.QuestionRepository
import com.example.testinterview.domain.model.Question
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuestionListUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    operator fun invoke(): Flow<List<Question>> {
        return questionRepository.getQuestionList()
    }
}