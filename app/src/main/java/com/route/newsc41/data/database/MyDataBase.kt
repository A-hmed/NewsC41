package com.route.newsc41.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.route.newsc41.data.database.dao.SourcesDao
import com.route.newsc41.data.model.SourceDM

@Database(entities = [SourceDM::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getSourcesDao(): SourcesDao

    companion object {
        const val TABLE_NAME = "News database"
        private var database: MyDatabase? = null
    }
}