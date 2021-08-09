package com.heronet.lplanner.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.heronet.lplanner.R
import com.heronet.lplanner.databinding.FragmentAddSubjectBinding
import com.heronet.lplanner.model.Subject
import com.heronet.lplanner.viewmodel.SubjectViewModel

class AddSubjectFragment : Fragment() {
    private lateinit var binding: FragmentAddSubjectBinding
    private val subjectViewModel: SubjectViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddSubjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            fbDone.setOnClickListener {
                val subject = Subject(title = etSubName.text.toString(), complete = false)
                subjectViewModel.addSubject(subject)
                findNavController().popBackStack()
            }
        }
    }

}