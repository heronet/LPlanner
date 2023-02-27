package com.heronet.lplanner.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.heronet.lplanner.databinding.FragmentAddItemBinding
import com.heronet.lplanner.model.Subject
import com.heronet.lplanner.model.Topic
import com.heronet.lplanner.util.Constants
import com.heronet.lplanner.viewmodel.SubjectViewModel

class AddItemFragment : Fragment() {
    private lateinit var binding: FragmentAddItemBinding
    private val subjectViewModel: SubjectViewModel by activityViewModels()
    private val args: AddItemFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            fbDone.setOnClickListener { onDone() }
            etSubName.setOnEditorActionListener { _, i, _ ->
                if(i == EditorInfo.IME_ACTION_DONE) {
                    onDone()
                }
                false
            }
        }
    }
    private fun onDone() {
        if (binding.etSubName.text.toString().isBlank()) {
            Toast.makeText(requireContext(), "You have to enter a title!", Toast.LENGTH_SHORT).show()
            return
        }

        when(args.mode) {
            Constants.ADD_SUBJECT -> {
                val subject = Subject(title = binding.etSubName.text.toString(), complete = false)
                subjectViewModel.addSubject(subject)
            }
            Constants.ADD_TOPIC -> {
                if (args.subjectId == -1L) return
                val topic = Topic(subjectId = args.subjectId, title = binding.etSubName.text.toString(), completedCount = 0)
                subjectViewModel.addTopic(topic)
            }
        }
        findNavController().popBackStack()
    }

}