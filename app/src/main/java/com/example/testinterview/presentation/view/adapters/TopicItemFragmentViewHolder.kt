package com.example.testinterview.presentation.view.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.testinterview.databinding.TopicItemBinding
import com.example.testinterview.domain.model.Topic

class TopicItemFragmentViewHolder(
    private val binding: TopicItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(topic: Topic) {
        binding.tvTopicName.text = topic.name
        binding.buttonDelete.tag = topic
        binding.buttonPlay.tag = topic
    }
}