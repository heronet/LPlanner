package com.heronet.lplanner.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.text.DateFormat
import java.util.*

@Entity(tableName = "topics")
data class Topic(
    @PrimaryKey(autoGenerate = true) val topicId: Long? = null,
    val subjectId: Long,
    val title: String,
    val completed: Boolean,
    val createdAt: Long = System.currentTimeMillis()
) {
    @Ignore
    val createdDateFormatted: String = DateFormat.getDateTimeInstance().format(createdAt)
}
