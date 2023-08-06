package com.example.testinterview.data

import com.example.testinterview.data.database.QuestionListDao
import com.example.testinterview.data.database.QuestionMapper
import com.example.testinterview.domain.QuestionRepository
import com.example.testinterview.domain.model.Category
import com.example.testinterview.domain.model.Question
import com.example.testinterview.domain.model.Topic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
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

    override fun getQuestionList(): Flow<List<Question>> = questionListDao.getQuestionList().map {
        mapper.mapListDbModelToListEntity(it)
    }

    override fun getLanguageQuestionList(): Flow<List<Question>> =
        questionListDao.getQuestionListSortedByCategory(Category.LANGUAGE).map {
            mapper.mapListDbModelToListEntity(it)
        }

    override fun getAndroidQuestionList(): Flow<List<Question>> =
        questionListDao.getQuestionListSortedByCategory(Category.ANDROID).map {
            mapper.mapListDbModelToListEntity(it)
        }
}