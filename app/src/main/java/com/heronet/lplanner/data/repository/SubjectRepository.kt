package com.heronet.lplanner.data.repository

import com.heronet.lplanner.data.db.SubjectsDao
import com.heronet.lplanner.model.Subject
import javax.inject.Inject

class SubjectRepository @Inject constructor(private val SubjectsDao: SubjectsDao) {
    fun getSubjects() = SubjectsDao.getSubjects()
    suspend fun addSubject(subject: Subject) = SubjectsDao.addSubject(subject)
}