package com.example.testinterview.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TopicListDao {

    @Query("SELECT * FROM topics")
    fun getTopicList(): LiveData<List<TopicDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTopic(topicDbModel: TopicDbModel)

    @Query("DELETE FROM topics WHERE id=:topicId")
    suspend fun deleteTopic(topicId: Int)

    @Query("SELECT * FROM topics WHERE id=:topicId LIMIT 1")
    suspend fun getTopic(topicId: Int): TopicDbModel

}