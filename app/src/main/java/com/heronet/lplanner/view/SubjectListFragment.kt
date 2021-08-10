package com.heronet.lplanner.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.heronet.lplanner.databinding.FragmentSubjectListBinding
import com.heronet.lplanner.utils.Constants.ADD_SUBJECT
import com.heronet.lplanner.viewmodel.SubjectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubjectListFragment : Fragment() {
    private lateinit var binding: FragmentSubjectListBinding
    private val subjectViewModel: SubjectViewModel by activityViewModels()
    private val rvAdapter: SubjectListAdapter by lazy { SubjectListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubjectListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvSubjectList.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }
        binding.addSubjectButton.setOnClickListener {
            val action = SubjectListFragmentDirections.actionSubjectListFragmentToAddSubjectFragment(mode = ADD_SUBJECT)
            findNavController().navigate(action)
        }
        subjectViewModel.subjects.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
            if (it.isEmpty())
                binding.nothingText.visibility = View.VISIBLE
            else
                binding.nothingText.visibility = View.GONE
        }
    }
}