package com.heronet.lplanner.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.heronet.lplanner.databinding.FragmentSubjectListBinding
import com.heronet.lplanner.util.Constants.ADD_SUBJECT
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
            layoutManager = GridLayoutManager(requireContext(), 2)
            hasFixedSize()
        }
        binding.addSubjectButton.setOnClickListener {
            val action = SubjectListFragmentDirections.actionSubjectListFragmentToAddSubjectFragment(mode = ADD_SUBJECT)
            findNavController().navigate(action)
        }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val subject = rvAdapter.currentList[viewHolder.adapterPosition]
                subjectViewModel.deleteSubject(subject)
                Snackbar.make(requireView(), "${subject.title} Deleted", Snackbar.LENGTH_SHORT).show()
            }

        }).attachToRecyclerView(binding.rvSubjectList)
        subjectViewModel.subjects.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
            if (it.isEmpty())
                binding.nothingText.visibility = View.VISIBLE
            else
                binding.nothingText.visibility = View.GONE
        }
    }
}