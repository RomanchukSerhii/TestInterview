package com.example.testinterview.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestionListDao {

    @Query("SELECT * FROM questions")
    fun getQuestionList(): LiveData<List<QuestionDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuestion(questionDbModel: QuestionDbModel)

    @Query("DELETE FROM questions WHERE id=:questionId")
    suspend fun deleteQuestion(questionId: Int)

    @Query("SELECT * FROM questions WHERE id=:questionId LIMIT 1")
    suspend fun getQuestion(questionId: Int): QuestionDbModel

}