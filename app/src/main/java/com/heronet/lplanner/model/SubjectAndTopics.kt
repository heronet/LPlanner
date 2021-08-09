package com.heronet.lplanner.model

import androidx.room.Embedded
import androidx.room.Relation

data class SubjectAndTopics(
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "subjectId",
        entityColumn = "subjectId"
    )
    val topics: List<Topic>
)
