package com.aplika.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aplika.core.database.model.CollectPointEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectPointDao {

    @Query("SELECT * FROM `collect_point` WHERE beachId = :beachId")
    fun getByBeachId(beachId: String): Flow<List<CollectPointEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<CollectPointEntity>)

}