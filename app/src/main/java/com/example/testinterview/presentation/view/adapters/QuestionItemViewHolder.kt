package com.example.testinterview.presentation.view.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.testinterview.databinding.QuestionItemBinding
import com.example.testinterview.domain.model.Question

class QuestionItemViewHolder(
    private val binding: QuestionItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(question: Question) {
        with(binding) {
            tvQuestionTitle.text = question.title
            tvQuestionAnswer.text = question.answer
            buttonDelete.tag = question
            buttonEdit.tag = question
        }

    }
}