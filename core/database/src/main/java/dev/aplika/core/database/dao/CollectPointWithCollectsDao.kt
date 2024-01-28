package dev.aplika.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import dev.aplika.core.database.model.CollectPointWithCollectsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectPointWithCollectsDao {

    @Transaction
    @Query("SELECT * FROM `collect_point`")
    fun getAll(): Flow<List<CollectPointWithCollectsEntity>>

    @Transaction
    @Query("SELECT * FROM `collect_point` WHERE id = :id")
    fun getById(id: String): Flow<CollectPointWithCollectsEntity>

}