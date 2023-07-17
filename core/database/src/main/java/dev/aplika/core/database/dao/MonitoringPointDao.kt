package dev.aplika.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import dev.aplika.core.database.model.MonitoringPointEntity
import dev.aplika.core.database.model.MonitoringPointWithCollectsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MonitoringPointDao {

    @Query("SELECT * FROM `monitoring_point`")
    fun getAll(): Flow<List<MonitoringPointEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<MonitoringPointEntity>)

}