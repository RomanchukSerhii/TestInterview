package com.example.testinterview.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testinterview.domain.model.Category
import com.example.testinterview.domain.model.Question
import com.example.testinterview.domain.usecase.AddQuestionUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddQuestionViewModel @Inject constructor(
    private val addQuestionUseCase: AddQuestionUseCase
) : ViewModel() {

    private val _state = MutableLiveData<AddQuestionState>()
    val state: LiveData<AddQuestionState> = _state


    fun switchCategory(category: Category) {
        _state.value = CategoryState(category)
    }

    fun addQuestion(
        category: Category,
        topic: String,
        title: String,
        answer: String
    ) {
        if (topic.isEmpty() || title.isEmpty() || answer.isEmpty()) {
            _state.value = Error
            return
        }
        val newQuestion = Question(
            category = category,
            topic = topic,
            title = title,
            answer = answer
        )
        viewModelScope.launch {
            addQuestionUseCase.invoke(newQuestion)
        }
    }
}