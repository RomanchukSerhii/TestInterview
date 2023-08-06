package com.example.testinterview.domain

import com.example.testinterview.domain.model.Question
import com.example.testinterview.domain.model.Topic
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {

    suspend fun addQuestion(question: Question)

    suspend fun deleteQuestion(questionId: Int)

    suspend fun editQuestion(question: Question)

    suspend fun getQuestion(questionId: Int): Question

    fun getQuestionList(): Flow<List<Question>>

    fun getLanguageQuestionList(): Flow<List<Question>>

    fun getAndroidQuestionList(): Flow<List<Question>>
}