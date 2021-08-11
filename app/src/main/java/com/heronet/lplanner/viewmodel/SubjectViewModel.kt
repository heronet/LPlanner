package com.heronet.lplanner.viewmodel

import androidx.lifecycle.*
import com.heronet.lplanner.data.repository.SubjectRepository
import com.heronet.lplanner.model.Subject
import com.heronet.lplanner.model.SubjectAndTopics
import com.heronet.lplanner.model.Topic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectViewModel @Inject constructor(private val repository: SubjectRepository): ViewModel() {
    var subjects: LiveData<List<Subject>> = repository.getSubjects().asLiveData()

    fun addSubject(subject: Subject) {
        viewModelScope.launch {
            repository.addSubject(subject)
        }
    }
    fun addTopic(topic: Topic) {
        viewModelScope.launch {
            repository.addTopic(topic)
        }
    }
}