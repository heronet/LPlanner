package com.heronet.lplanner.view

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.heronet.lplanner.databinding.TopicItemBinding
import com.heronet.lplanner.model.Topic

class SubjectTopicListAdapter: ListAdapter<Topic, SubjectTopicListAdapter.SubjectTopicListViewHolder>(SubjectTopicListDiffUtil()) {
    class SubjectTopicListViewHolder(private val binding: TopicItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(topic: Topic) {
            binding.apply {
                textView.text = topic.title
                textView.paintFlags = if (topic.completed) textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG else 0
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectTopicListViewHolder {
        return SubjectTopicListViewHolder(TopicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SubjectTopicListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}
class SubjectTopicListDiffUtil: DiffUtil.ItemCallback<Topic>() {
    override fun areItemsTheSame(oldItem: Topic, newItem: Topic): Boolean {
        return oldItem.topicId == newItem.topicId
    }
    override fun areContentsTheSame(oldItem: Topic, newItem: Topic): Boolean {
        return oldItem.title == newItem.title
    }
}