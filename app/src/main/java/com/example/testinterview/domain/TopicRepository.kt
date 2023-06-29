package com.example.testinterview.domain

import androidx.lifecycle.LiveData
import com.example.testinterview.domain.model.Topic

interface TopicRepository {

    suspend fun addTopic(topic: Topic)

    suspend fun deleteTopic(topicId: Int)

    suspend fun editTopic(topic: Topic)

    suspend fun getTopic(topicId: Int): Topic

    fun getTopicList(): LiveData<List<Topic>>
}