package com.heronet.lplanner.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.heronet.lplanner.model.Subject
import com.heronet.lplanner.model.SubjectAndTopics
import com.heronet.lplanner.model.Topic
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectsDao {
    @Query("SELECT * FROM subjects")
    fun getSubjects(): Flow<List<Subject>>

    @Transaction
    @Query("SELECT * FROM subjects WHERE subjectId=:subjectId")
    fun getSubjectTopics(subjectId: Long): Flow<List<SubjectAndTopics>>

    @Insert
    suspend fun addSubject(subject: Subject)

    @Insert
    suspend fun addTopic(topic: Topic)

}