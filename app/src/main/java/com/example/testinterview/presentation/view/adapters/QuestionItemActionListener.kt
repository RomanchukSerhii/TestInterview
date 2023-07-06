package com.example.testinterview.presentation.view.adapters

import com.example.testinterview.domain.model.Question

interface QuestionItemActionListener {

    fun onDeleteButtonClick(question: Question)

    fun onEditButtonClick(question: Question)
}