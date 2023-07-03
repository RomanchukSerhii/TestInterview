package com.example.testinterview.presentation.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testinterview.domain.model.Topic
import com.example.testinterview.domain.usecase.GetQuestionListUseCase
import javax.inject.Inject

class ListTopicViewModel @Inject constructor(
    private val getQuestionListUseCase: GetQuestionListUseCase
) : ViewModel() {

    private val questionListLD = getQuestionListUseCase.invoke()

    private val _topicListLD = MutableLiveData<List<Topic>>()
    val topicListLD: LiveData<List<Topic>> = _topicListLD

    init {
        questionListLD.observeForever { questionList ->
            questionList?.let { list ->
                val topicList = mutableListOf<Topic>()
                list.forEach { question ->
                    if (!topicList.contains(question.topic)) {
                        topicList.add(question.topic)
                    }
                }
                _topicListLD.value = topicList
            }
        }
    }

    fun onCleared(owner: LifecycleOwner) {
        questionListLD.removeObservers(owner)
        super.onCleared()
    }
}