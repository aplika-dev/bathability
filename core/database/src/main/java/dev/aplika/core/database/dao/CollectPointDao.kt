package dev.aplika.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import dev.aplika.core.database.model.CollectPointEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectPointDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<CollectPointEntity>)

    @Transaction
    @Query("SELECT * FROM `collect_point`")
    fun getAll(): Flow<List<CollectPointEntity>>

}