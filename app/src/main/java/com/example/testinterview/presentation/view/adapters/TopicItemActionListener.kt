package com.example.testinterview.presentation.view.adapters

import com.example.testinterview.domain.model.Topic

interface TopicItemActionListener {

    fun onTopicItemClick(topic: Topic)

    fun onDeleteButtonClick(topic: Topic)

    fun onPlayButtonClick(topic: Topic)
}