package com.example.testinterview.presentation.view.adapters

import android.view.View
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
            buttonShowAnswer.text = root.context.getString(R.string.show_answer)
            tvQuestionTitle.text = question.title
            tvQuestionAnswer.visibility = View.GONE
            tvQuestionAnswer.text = question.answer
            tvCountQuestion.text = String.format(
                root.context.getString(R.string.count_question),
                currentQuestionNumber,
                totalQuestionCount
            )

            buttonDelete.tag = question
            buttonEdit.tag = question
            buttonShowAnswer.tag = this@QuestionItemViewHolder
        }
    }

    fun showAnswer() {
        with(binding) {
            tvQuestionAnswer.visibility = View.VISIBLE
            val currentButtonText = root.context.getString(R.string.next_question)
            buttonShowAnswer.text = currentButtonText
            buttonShowAnswer.tag = currentButtonText
        }
    }
}