package com.example.testinterview.domain

import androidx.lifecycle.LiveData
import com.example.testinterview.domain.model.Question

interface QuestionRepository {

    fun addQuestion(question: Question)

    fun deleteQuestion(question: Question)

    fun editQuestion(question: Question)

    fun getQuestion(questionId: Int): LiveData<Question>

    fun getListQuestion(): LiveData<List<Question>>
}