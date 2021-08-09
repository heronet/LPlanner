package com.heronet.lplanner.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.heronet.lplanner.R
import com.heronet.lplanner.databinding.FragmentSubjectListBinding
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
        }
        binding.addSubjectButton.setOnClickListener {
            findNavController().navigate(R.id.action_subjectListFragment_to_addSubjectFragment)
        }
        subjectViewModel.subjects.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
        }
    }
}