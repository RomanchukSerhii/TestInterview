package com.example.testinterview.domain.usecase

import com.example.testinterview.domain.TopicRepository
import com.example.testinterview.domain.model.Topic

class AddTopicUseCase(
    private val topicRepository: TopicRepository
) {
    suspend operator fun invoke(topic: Topic) {
        topicRepository.addTopic(topic)
    }
}