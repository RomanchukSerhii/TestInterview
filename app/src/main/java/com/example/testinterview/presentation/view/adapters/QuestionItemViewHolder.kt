package com.example.testinterview.presentation.view.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.testinterview.R
import com.example.testinterview.databinding.QuestionItemBinding
import com.example.testinterview.domain.model.Question

class QuestionItemViewHolder(
    private val binding: QuestionItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        question: Question,
        totalQuestionCount: Int,
        currentQuestionNumber: Int
    ) {
        with(binding) {
            tvQuestionTitle.text = question.title
            tvQuestionAnswer.text = question.answer
            tvCountQuestion.text = String.format(
                root.context.getString(R.string.count_question),
                currentQuestionNumber,
                totalQuestionCount
            )
            buttonDelete.tag = question
            buttonEdit.tag = question
        }

    }
}