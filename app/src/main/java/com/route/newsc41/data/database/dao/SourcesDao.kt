package com.route.newsc41.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.route.newsc41.data.model.SourceDM

@Dao
interface SourcesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSources(sources: List<SourceDM>)

    @Query("select * from SourceDM where category = :categoryId")
    suspend fun getSources(categoryId: String): List<SourceDM>
}