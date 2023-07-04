package com.example.testinterview.presentation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testinterview.R
import com.example.testinterview.databinding.MoreTopicItemBinding
import com.example.testinterview.databinding.TopicItemBinding
import com.example.testinterview.domain.model.Topic

class TopicListAdapter(
    private val launchMode: TopicListLaunchMode,
    private val actionListener: TopicItemActionListener
) : ListAdapter<Topic, RecyclerView.ViewHolder>(DiffCallback), View.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (launchMode == TopicListLaunchMode.DIALOG){
            val binding = MoreTopicItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            binding.root.setOnClickListener(this)
            TopicItemDialogViewHolder(binding)
        } else {
            val binding = TopicItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            binding.buttonPlay.setOnClickListener(this)
            binding.buttonDelete.setOnClickListener(this)
            TopicItemFragmentViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val topic = getItem(position)
        if (launchMode == TopicListLaunchMode.DIALOG) {
            (holder as TopicItemDialogViewHolder).bind(topic)
        } else {
            (holder as TopicItemFragmentViewHolder).bind(topic)
        }
    }

    override fun onClick(v: View?) {
        val topic = v?.tag as Topic
        when(v.id) {
            R.id.button_delete -> actionListener.onDeleteButtonClick(topic)
            R.id.button_play -> actionListener.onPlayButtonClick(topic)
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