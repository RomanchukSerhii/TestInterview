package com.example.testinterview.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.testinterview.data.database.QuestionListDao
import com.example.testinterview.data.database.QuestionMapper
import com.example.testinterview.domain.QuestionRepository
import com.example.testinterview.domain.model.Question

class QuestionRepositoryImpl(
    private val questionListDao: QuestionListDao,
    private val mapper: QuestionMapper
) : QuestionRepository {
    override suspend fun addQuestion(question: Question) {
        val dbModel = mapper.mapEntityToDbModel(question)
        questionListDao.addQuestion(dbModel)
    }

    override suspend fun deleteQuestion(questionId: Int) {
        questionListDao.deleteQuestion(questionId)
    }

    override suspend fun editQuestion(question: Question) {
        val dbModel = mapper.mapEntityToDbModel(question)
        questionListDao.addQuestion(dbModel)
    }

    override suspend fun getQuestion(questionId: Int): Question {
        val dbModel = questionListDao.getQuestion(questionId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getQuestionList(): LiveData<List<Question>> = questionListDao.getQuestionList().map {
        mapper.mapListDbModelToListEntity(it)
    }
}