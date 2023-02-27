package com.heronet.lplanner.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.text.DateFormat

@Entity(tableName = "topics",
    foreignKeys = [
        ForeignKey(
            entity = Subject::class,
            onDelete = CASCADE,
            parentColumns = ["subjectId"],
            childColumns = ["subjectId"]
        )
    ]
)
data class Topic(
    @PrimaryKey(autoGenerate = true) val topicId: Long? = null,
    val subjectId: Long,
    val title: String,
    val completedCount: Long,
    val createdAt: Long = System.currentTimeMillis(),
) {
    @Ignore
    val createdDateFormatted: String = DateFormat.getDateTimeInstance().format(createdAt)
}
