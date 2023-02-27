package com.heronet.lplanner.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.heronet.lplanner.databinding.TopicItemBinding
import com.heronet.lplanner.model.Topic

class SubjectTopicListAdapter(val topicDone: (Topic) -> Unit): ListAdapter<Topic, SubjectTopicListAdapter.SubjectTopicListViewHolder>(
    SubjectTopicListDiffUtil()
) {
    inner class SubjectTopicListViewHolder(private val binding: TopicItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(topic: Topic) {
            binding.apply {
                taskName.text = topic.title
                date.text = topic.createdDateFormatted
                var completedCount = topic.completedCount
                runCount.text = completedCount.toString()
                increaseCount.setOnClickListener {
                    completedCount += 1
                    topicDone(topic.copy(completedCount = completedCount))
                    runCount.text = completedCount.toString()
                }
                decreaseCount.setOnClickListener {
                    if (completedCount > 0) {
                        completedCount -= 1
                        topicDone(topic.copy(completedCount = completedCount))
                        runCount.text = completedCount.toString()
                    }
                }
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