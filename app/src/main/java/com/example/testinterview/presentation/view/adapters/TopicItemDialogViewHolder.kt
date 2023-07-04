package com.example.testinterview.presentation.view.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.testinterview.databinding.MoreTopicItemBinding
import com.example.testinterview.domain.model.Topic

class TopicItemDialogViewHolder(
    private val binding: MoreTopicItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(topic: Topic) {
        binding.tvTopicName.text = topic.name
        itemView.tag = topic
    }
}