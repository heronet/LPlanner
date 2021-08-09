package com.heronet.lplanner.data

import android.content.Context
import androidx.room.Room
import com.heronet.lplanner.data.db.LPlannerDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providelplannerDb(@ApplicationContext context: Context) = Room
        .databaseBuilder(context, LPlannerDb::class.java, "lplanner_db")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun providelplannerDao(db: LPlannerDb) = db.getlplannerDao()
}