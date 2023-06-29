package com.example.testinterview.domain.usecase

import androidx.lifecycle.LiveData
import com.example.testinterview.domain.TopicRepository
import com.example.testinterview.domain.model.Topic

class GetTopicListUseCase(
    private val questionRepository: TopicRepository
) {
    operator fun invoke(): LiveData<List<Topic>> {
        return questionRepository.getTopicList()
    }
}