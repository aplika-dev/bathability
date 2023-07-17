package dev.aplika.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import dev.aplika.core.database.model.MonitoringPointCollectEntity

@Dao
interface MonitoringPointCollectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<MonitoringPointCollectEntity>)

}