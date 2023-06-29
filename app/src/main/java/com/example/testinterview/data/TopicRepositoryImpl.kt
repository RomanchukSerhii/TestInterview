package com.example.testinterview.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.testinterview.data.database.TopicListDao
import com.example.testinterview.data.database.TopicMapper
import com.example.testinterview.domain.TopicRepository
import com.example.testinterview.domain.model.Topic

class TopicRepositoryImpl(
    private val topicListDao: TopicListDao,
    private val mapper: TopicMapper
) : TopicRepository {
    override suspend fun addTopic(topic: Topic) {
        val dbModel = mapper.mapEntityToDbModel(topic)
        topicListDao.addTopic(dbModel)
    }

    override suspend fun deleteTopic(topicId: Int) {
        topicListDao.deleteTopic(topicId)
    }

    override suspend fun editTopic(topic: Topic) {
        val dbModel = mapper.mapEntityToDbModel(topic)
        topicListDao.addTopic(dbModel)
    }

    override suspend fun getTopic(topicId: Int): Topic {
        val dbModel = topicListDao.getTopic(topicId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getTopicList(): LiveData<List<Topic>> = topicListDao.getTopicList().map {
        mapper.mapListDbModelToListEntity(it)
    }
}