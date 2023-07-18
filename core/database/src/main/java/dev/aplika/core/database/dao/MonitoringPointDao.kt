package dev.aplika.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.aplika.core.database.model.MonitoringPointEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MonitoringPointDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<MonitoringPointEntity>)

    @Query("SELECT * FROM `monitoring_point`")
    fun getAll(): Flow<List<MonitoringPointEntity>>

}