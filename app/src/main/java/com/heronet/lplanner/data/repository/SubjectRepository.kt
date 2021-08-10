package com.heronet.lplanner.data.repository

import com.heronet.lplanner.data.db.SubjectsDao
import com.heronet.lplanner.model.Subject
import com.heronet.lplanner.model.Topic
import javax.inject.Inject

class SubjectRepository @Inject constructor(private val subjectsDao: SubjectsDao) {
    fun getSubjects() = subjectsDao.getSubjects()
    fun getSubjectTopics(id: Long) = subjectsDao.getSubjectTopics(id)
    suspend fun addSubject(subject: Subject) = subjectsDao.addSubject(subject)
    suspend fun addTopic(topic: Topic) = subjectsDao.addTopic(topic)
}