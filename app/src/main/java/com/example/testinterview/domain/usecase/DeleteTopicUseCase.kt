package com.example.testinterview.domain.usecase

import com.example.testinterview.domain.TopicRepository
import com.example.testinterview.domain.model.Topic

class DeleteTopicUseCase(
    private val questionRepository: TopicRepository
) {
    suspend operator fun invoke(topicId: Int) {
        questionRepository.deleteTopic(topicId)
    }
}