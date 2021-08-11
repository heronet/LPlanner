package com.heronet.lplanner.data.db

import androidx.room.*
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
    fun getSubjectTopics(subjectId: Long): Flow<SubjectAndTopics>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSubject(subject: Subject)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTopic(topic: Topic)

    @Update
    suspend fun updateTopic(topic: Topic)

    @Delete
    suspend fun deleteTopic(topic: Topic)

}