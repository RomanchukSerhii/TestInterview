package com.example.testinterview.domain

import androidx.lifecycle.LiveData
import com.example.testinterview.domain.model.Topic

interface TopicRepository {

    fun addTopic(topic: Topic)

    fun deleteTopic(topic: Topic)

    fun editTopic(topic: Topic)

    fun getTopic(topicId: Int): Topic

    fun getTopicList(): LiveData<List<Topic>>
}