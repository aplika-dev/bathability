package dev.aplika.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import dev.aplika.core.database.model.MonitoringPointWithCollectsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MonitoringPointWithCollectsDao {

    @Transaction
    @Query("SELECT * FROM `beach`")
    fun getAll(): Flow<List<MonitoringPointWithCollectsEntity>>

    @Transaction
    @Query("SELECT * FROM `beach` WHERE id = :id")
    fun getById(id: String): Flow<MonitoringPointWithCollectsEntity>

}