package com.example.testinterview.domain.usecase

import com.example.testinterview.domain.TopicRepository
import com.example.testinterview.domain.model.Topic

class DeleteTopicUseCase(
    private val questionRepository: TopicRepository
) {
    operator fun invoke(topic: Topic) {
        questionRepository.deleteTopic(topic)
    }
}