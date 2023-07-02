package com.example.testinterview.presentation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.testinterview.databinding.MoreTopicItemBinding
import com.example.testinterview.domain.model.Topic

class TopicListAdapter(
    private val actionListener: TopicItemActionInterface
) : ListAdapter<Topic, TopicItemViewHolder>(DiffCallback), View.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicItemViewHolder {
        val binding = MoreTopicItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        binding.root.setOnClickListener(this)
        return TopicItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicItemViewHolder, position: Int) {
        val topic = getItem(position)
        holder.bind(topic)
    }

    override fun onClick(v: View?) {
        val topic = v?.tag as Topic
        when(v.id) {
            else -> actionListener.onTopicItemClick(topic)
        }
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