package com.example.testinterview.presentation.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testinterview.domain.model.Category
import com.example.testinterview.domain.model.Question
import com.example.testinterview.domain.usecase.DeleteQuestionUseCase
import com.example.testinterview.domain.usecase.GetQuestionListUseCase
import com.example.testinterview.presentation.view.screens.InterviewFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class InterviewViewModel @Inject constructor(
    private val getQuestionListUseCase: GetQuestionListUseCase,
    private val deleteQuestionUseCase: DeleteQuestionUseCase
) : ViewModel() {

    private val questionListFromDB = getQuestionListUseCase.invoke()

    private val _questionListLD = MutableLiveData<List<Question>>()
    val questionListLD: LiveData<List<Question>> = _questionListLD

    private var shuffleMode = Question.SHUFFLE_MODE_OFF
    private var notShuffledList = listOf<Question>()

    fun initList(lifecycleOwner: LifecycleOwner, questionMode: String, topicName: String) {
        questionListFromDB.observeOnce(lifecycleOwner) {
            val questionList = when (questionMode) {
                InterviewFragment.GENERAL_MODE -> it
                InterviewFragment.LANGUAGE_MODE -> {
                    it.filter { question -> question.category == Category.LANGUAGE }
                }
                InterviewFragment.ANDROID_MODE -> {
                    it.filter { question -> question.category == Category.ANDROID }
                }
                InterviewFragment.TOPIC_MODE -> {
                    it.filter { question -> question.topic.name == topicName }
                }
                else -> throw RuntimeException("Unknown questionMode: $questionMode")
            }

            _questionListLD.value = questionList
            notShuffledList = questionList
        }
    }

    fun deleteQuestion(question: Question) {
        viewModelScope.launch {
            deleteQuestionUseCase.invoke(questionId = question.id)
        }
    }

    fun switchShuffleMode() {
        shuffleMode = !shuffleMode
        val questionList = questionListLD.value
        questionList?.let { list ->
            if (shuffleMode) {
                val shuffleList= list.shuffled()
                _questionListLD.value = shuffleList.map { it.copy(shuffleMode = shuffleMode) }
            } else {
                _questionListLD.value = notShuffledList
            }
        }
    }
}