package com.heronet.lplanner.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.heronet.lplanner.R
import com.heronet.lplanner.databinding.FragmentSubjectTopicListBinding

class SubjectTopicListFragment : Fragment() {
    private lateinit var binding: FragmentSubjectTopicListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubjectTopicListBinding.inflate(inflater, container, false)
        return binding.root
    }

}