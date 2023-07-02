package com.example.testinterview.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testinterview.domain.usecase.GetQuestionListUseCase
import javax.inject.Inject

class ListTopicViewModel @Inject constructor(
    private val getQuestionListUseCase: GetQuestionListUseCase
) : ViewModel() {

    private val questionListLD = getQuestionListUseCase.invoke()

    private val _topicListLD = MutableLiveData<List<String>>()
    val topicListLD: LiveData<List<String>> = _topicListLD

    init {
        val questionList = questionListLD.value
        questionList?.let { list ->
            val topicList = mutableListOf<String>()
            list.forEach { question ->
                if (!list.contains(question)) {
                    topicList.add(question.topic)
                }
            }
            _topicListLD.value = topicList
        }
    }
}