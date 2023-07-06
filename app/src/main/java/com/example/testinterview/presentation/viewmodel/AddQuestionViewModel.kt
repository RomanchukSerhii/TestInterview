package com.example.testinterview.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testinterview.domain.model.Category
import com.example.testinterview.domain.model.Question
import com.example.testinterview.domain.model.Topic
import com.example.testinterview.domain.usecase.AddQuestionUseCase
import com.example.testinterview.domain.usecase.EditQuestionUseCase
import com.example.testinterview.domain.usecase.GetQuestionUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddQuestionViewModel @Inject constructor(
    private val addQuestionUseCase: AddQuestionUseCase,
    private val getQuestionUseCase: GetQuestionUseCase,
    private val editQuestionUseCase: EditQuestionUseCase
) : ViewModel() {

    private val _state = MutableLiveData<AddQuestionState>()
    val state: LiveData<AddQuestionState> = _state

    init {
        _state.value = CategoryState(Category.LANGUAGE)
    }

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
            topic = Topic(topic),
            title = title,
            answer = answer
        )
        viewModelScope.launch {
            addQuestionUseCase.invoke(newQuestion)
        }
    }

    fun editQuestion(
        questionId: Int,
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
            id = questionId,
            category = category,
            topic = Topic(topic),
            title = title,
            answer = answer
        )
        viewModelScope.launch {
            editQuestionUseCase.invoke(newQuestion)
        }
    }

    fun getQuestionItem(questionId: Int) {
        viewModelScope.launch {
            val question = getQuestionUseCase.invoke(questionId)
            _state.value = QuestionItem(question)
        }
    }
}