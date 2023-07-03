package com.example.testinterview.presentation.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.testinterview.domain.model.Question
import com.example.testinterview.domain.model.Topic
import com.example.testinterview.domain.usecase.GetQuestionListUseCase
import javax.inject.Inject

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(value: T) {
            observer.onChanged(value)
            removeObserver(this)
        }
    })
}

class ListTopicViewModel @Inject constructor(
    private val getQuestionListUseCase: GetQuestionListUseCase
) : ViewModel() {

    private val questionListLD = getQuestionListUseCase.invoke()
    private val topicListLD = questionListLD.map { mapQuestionListToTopicList(it) }

    private val _filteredTopicListLD = MutableLiveData<List<Topic>>()
    val filteredTopicListLD: LiveData<List<Topic>> = _filteredTopicListLD

    fun initList(lifecycleOwner: LifecycleOwner) {
        topicListLD.observeOnce(lifecycleOwner) {
            _filteredTopicListLD.value = it
        }
    }

    private fun mapQuestionListToTopicList(questionList: List<Question>): List<Topic> {
        val topicList = mutableListOf<Topic>()
        questionList.forEach { question ->
            if (!topicList.contains(question.topic)) {
                topicList.add(question.topic)
            }
        }
        return topicList
    }

    fun searchTopic(substring: String) {
        val topicList = topicListLD.value
        topicList?.let {
            val filteredTopicList = it.filter {
                topic -> topic.name.lowercase().contains(substring.lowercase())
            }
            _filteredTopicListLD.value = filteredTopicList
        }
    }
}