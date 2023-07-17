package dev.aplika.core.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.aplika.core.database.dao.MonitoringPointDao
import dev.aplika.core.database.dao.MonitoringPointWithCollectsDao
import dev.aplika.core.database.dao.MonitoringPointCollectDao
import dev.aplika.core.database.model.MonitoringPointEntity
import dev.aplika.core.database.model.MonitoringPointCollectEntity

@Database(
    entities = [
        MonitoringPointEntity::class,
        MonitoringPointCollectEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class BathabilityDatabase : RoomDatabase() {
    abstract fun monitoringPointWithCollectsDao(): MonitoringPointWithCollectsDao
    abstract fun monitoringPointDao(): MonitoringPointDao
    abstract fun monitoringPointCollectDao(): MonitoringPointCollectDao
}