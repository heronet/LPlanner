package com.heronet.lplanner.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class Subject(
    @PrimaryKey(autoGenerate = true) val subjectId: Long? = null,
    val title: String,
    val complete: Boolean
)
