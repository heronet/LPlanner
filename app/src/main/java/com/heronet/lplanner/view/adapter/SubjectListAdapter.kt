package com.heronet.lplanner.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.heronet.lplanner.databinding.SubjectItemBinding
import com.heronet.lplanner.model.Subject

class SubjectListAdapter: ListAdapter<Subject, SubjectListAdapter.SubjectListViewHolder>(SubjectListDiffUtil()) {
    class SubjectListViewHolder(private val binding: SubjectItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(subject: Subject) {
            binding.apply {
                subjectName.text = subject.title
                itemCard.setOnClickListener {
                    val action = SubjectListFragmentDirections.actionSubjectListFragmentToSubjectTopicListFragment(subject.subjectId!!, subjectName = subject.title)
                    itemView.findNavController().navigate(action)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectListViewHolder {
        return SubjectListViewHolder(SubjectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SubjectListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
class SubjectListDiffUtil: DiffUtil.ItemCallback<Subject>() {
    override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean {
        return oldItem.subjectId == newItem.subjectId
    }

    override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean {
        return oldItem.title == newItem.title
    }


}