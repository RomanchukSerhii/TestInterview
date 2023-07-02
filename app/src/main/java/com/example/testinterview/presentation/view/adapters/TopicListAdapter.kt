package com.example.testinterview.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.testinterview.databinding.MoreTopicItemBinding
import com.example.testinterview.domain.model.Topic

class TopicListAdapter : ListAdapter<Topic, TopicItemViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicItemViewHolder {
        val binding = MoreTopicItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TopicItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicItemViewHolder, position: Int) {
        val topic = getItem(position)
        holder.bind(topic)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Topic>() {
            override fun areItemsTheSame(oldItem: Topic, newItem: Topic): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Topic, newItem: Topic): Boolean {
                return oldItem == newItem
            }
        }
    }
}