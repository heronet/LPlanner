package com.heronet.lplanner.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.heronet.lplanner.data.repository.SubjectRepository
import com.heronet.lplanner.model.SubjectAndTopics
import com.heronet.lplanner.model.Topic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicsViewModel @Inject constructor(private val repository: SubjectRepository): ViewModel() {

    fun getTopics(id: Long): LiveData<SubjectAndTopics> {
        return repository.getSubjectTopics(id).asLiveData()
    }
    fun updateTopic(topic: Topic) {
        viewModelScope.launch {
            repository.updateTopic(topic)
        }
    }
}