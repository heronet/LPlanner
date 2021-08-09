package com.heronet.lplanner.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "topics")
data class Topic(
    @PrimaryKey(autoGenerate = true) val topicId: Long,
    val subjectId: Long,
    val title: String?,
    val completed: Boolean,
    val createdAt: Date?
)
