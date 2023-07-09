package com.example.testinterview.presentation.viewmodel

import android.text.BoringLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testinterview.domain.model.Question
import com.example.testinterview.domain.usecase.DeleteQuestionUseCase
import com.example.testinterview.domain.usecase.EditQuestionUseCase
import com.example.testinterview.domain.usecase.GetQuestionListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class InterviewViewModel @Inject constructor(
    private val getQuestionListUseCase: GetQuestionListUseCase,
    private val deleteQuestionUseCase: DeleteQuestionUseCase
) : ViewModel() {
    val questionListLD = getQuestionListUseCase.invoke()

    private val _isShuffled = MutableLiveData<Boolean>()
    val isShuffled: LiveData<Boolean> = _isShuffled

    fun deleteQuestion(question: Question) {
        viewModelScope.launch {
            deleteQuestionUseCase.invoke(questionId = question.id)
        }
    }

    fun shuffleList(state: Boolean) {
        _isShuffled.value = state
    }
}