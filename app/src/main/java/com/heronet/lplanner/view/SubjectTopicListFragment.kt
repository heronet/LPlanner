package com.heronet.lplanner.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.heronet.lplanner.databinding.FragmentSubjectTopicListBinding
import com.heronet.lplanner.model.Topic
import com.heronet.lplanner.utils.Constants.ADD_TOPIC
import com.heronet.lplanner.viewmodel.SubjectViewModel
import com.heronet.lplanner.viewmodel.TopicsViewModel

class SubjectTopicListFragment : Fragment() {
    private lateinit var binding: FragmentSubjectTopicListBinding
    private val topicsViewModel: TopicsViewModel by activityViewModels()
    private val args: SubjectTopicListFragmentArgs by navArgs()
    private val rvAdapter: SubjectTopicListAdapter by lazy {
        SubjectTopicListAdapter {
            topicsViewModel.updateTopic(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubjectTopicListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvTopicList.apply {
                adapter = rvAdapter
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(
                    DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
                )
                hasFixedSize()
            }
            addTopicButton.setOnClickListener {
                val action = SubjectTopicListFragmentDirections
                    .actionSubjectTopicListFragmentToAddSubjectFragment(
                        mode = ADD_TOPIC,
                        subjectId = args.id
                    )
                findNavController().navigate(action)
            }
        }
        topicsViewModel.getTopics(args.id).observe(viewLifecycleOwner) {
            rvAdapter.submitList(it.topics)
            if (it.topics.isEmpty())
                binding.nothingText.visibility = View.VISIBLE
            else
                binding.nothingText.visibility = View.GONE
        }
    }
}