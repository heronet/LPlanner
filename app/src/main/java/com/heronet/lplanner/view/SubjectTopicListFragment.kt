package com.heronet.lplanner.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.heronet.lplanner.databinding.FragmentSubjectTopicListBinding
import com.heronet.lplanner.utils.Constants.ADD_TOPIC
import com.heronet.lplanner.viewmodel.SubjectViewModel

class SubjectTopicListFragment : Fragment() {
    private lateinit var binding: FragmentSubjectTopicListBinding
    private val subjectViewModel: SubjectViewModel by activityViewModels()
    private val args: SubjectTopicListFragmentArgs by navArgs()
    private val rvAdapter: SubjectTopicListAdapter by lazy { SubjectTopicListAdapter() }

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
            rvTopicList.adapter = rvAdapter
            rvTopicList.layoutManager = LinearLayoutManager(requireContext())
            rvTopicList.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
            addTopicButton.setOnClickListener {
                val action = SubjectTopicListFragmentDirections.actionSubjectTopicListFragmentToAddSubjectFragment(mode = ADD_TOPIC, subjectId = args.id)
                findNavController().navigate(action)
            }
        }
        subjectViewModel.getSubjectTopics(args.id).observe(viewLifecycleOwner) {
            rvAdapter.submitList(it.topics)
            if (it.topics.isEmpty())
                binding.nothingText.visibility = View.VISIBLE
            else
                binding.nothingText.visibility = View.GONE
        }
    }
}