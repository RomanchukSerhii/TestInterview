package com.example.testinterview.domain.usecase

import com.example.testinterview.domain.TopicRepository
import com.example.testinterview.domain.model.Topic

class GetTopicUseCase (
    private val topicRepository: TopicRepository
) {
    operator fun invoke(topicId: Int): Topic {
        return topicRepository.getTopic(topicId)
    }
}