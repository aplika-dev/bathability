package dev.aplika.core.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.aplika.core.database.dao.MonitoringPointDao
import dev.aplika.core.database.model.MonitoringPointEntity

@Database(
    entities = [
        MonitoringPointEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BathabilityDatabase : RoomDatabase() {
    abstract fun monitoringPointDao(): MonitoringPointDao
}