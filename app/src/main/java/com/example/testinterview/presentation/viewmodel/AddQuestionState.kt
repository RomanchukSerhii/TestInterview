package com.example.testinterview.presentation.viewmodel

import com.example.testinterview.domain.model.Category
import com.example.testinterview.domain.model.Question

sealed class AddQuestionState

object Error : AddQuestionState()

class CategoryState(val category: Category) : AddQuestionState()

class QuestionItem(val question: Question) : AddQuestionState()