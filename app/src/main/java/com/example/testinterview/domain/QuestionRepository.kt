package com.example.testinterview.domain

import androidx.lifecycle.LiveData
import com.example.testinterview.domain.model.Question

interface QuestionRepository {

    suspend fun addQuestion(question: Question)

    suspend fun deleteQuestion(questionId: Int)

    suspend fun editQuestion(question: Question)

    suspend fun getQuestion(questionId: Int): Question

    fun getQuestionList(): LiveData<List<Question>>
}