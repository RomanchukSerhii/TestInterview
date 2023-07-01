package com.example.testinterview.presentation.viewmodel

import com.example.testinterview.domain.model.Category

sealed class AddQuestionState

object Error : AddQuestionState()

class CategoryState(val category: Category) : AddQuestionState()