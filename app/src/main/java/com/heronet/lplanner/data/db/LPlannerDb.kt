package com.heronet.lplanner.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.heronet.lplanner.model.Subject
import com.heronet.lplanner.model.Topic
import com.heronet.lplanner.utils.Converters

@Database(entities = [Subject::class, Topic::class], version = 1)
@TypeConverters(Converters::class)
abstract class LPlannerDb: RoomDatabase() {
    abstract fun getSubjectsDao(): SubjectsDao
}