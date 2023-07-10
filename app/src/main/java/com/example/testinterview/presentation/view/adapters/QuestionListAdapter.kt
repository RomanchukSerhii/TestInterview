package com.example.testinterview.presentation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.testinterview.R
import com.example.testinterview.databinding.QuestionItemBinding
import com.example.testinterview.domain.model.Question

class QuestionListAdapter(
    private val actionListener: QuestionItemActionListener
) : ListAdapter<Question, QuestionItemViewHolder>(DiffCallback), View.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionItemViewHolder {
        val binding = QuestionItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        binding.buttonDelete.setOnClickListener(this)
        binding.buttonEdit.setOnClickListener(this)
        binding.buttonShowAnswer.setOnClickListener(this)
        binding.buttonShuffle.setOnClickListener(this)
        return QuestionItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionItemViewHolder, position: Int) {
        val question = getItem(position)

        holder.bind(
            question = question,
            totalQuestionCount = currentList.size,
            currentQuestionNumber = position + 1,
            shuffleMode = question.shuffleMode
        )
    }

    override fun getItemViewType(position: Int): Int {
        val question = getItem(position)
        return if (question.shuffleMode == Question.SHUFFLE_MODE_OFF) {
            SHUFFLE_MODE_OFF
        } else {
            SHUFFLE_MODE_ON
        }
    }

    override fun onClick(v: View?) {
        when (v?.tag) {
            is Question -> {
                val question = v.tag as Question
                when (v.id) {
                    R.id.button_delete -> actionListener.onDeleteButtonClick(question)
                    R.id.button_edit -> actionListener.onEditButtonClick(question)
                }
            }
            is QuestionItemViewHolder -> {
                val viewHolder = v.tag as QuestionItemViewHolder
                when (v.id) {
                    R.id.button_show_answer -> actionListener.onShowAnswerButtonClick(viewHolder)
                }
            }
            else -> {
                when (v?.id) {
                    R.id.button_show_answer -> actionListener.onNextQuestionButtonClick()
                    R.id.button_shuffle -> actionListener.onShuffleButtonClick()
                }
            }
        }

    }

    companion object {
        private const val SHUFFLE_MODE_ON = 1
        private const val SHUFFLE_MODE_OFF = 0

        private val DiffCallback = object : DiffUtil.ItemCallback<Question>() {
            override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
                return oldItem == newItem
            }
        }
    }
}