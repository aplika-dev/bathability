package dev.aplika.core.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.aplika.core.database.dao.CollectPointDao
import dev.aplika.core.database.dao.CollectPointDetailedDao
import dev.aplika.core.database.dao.CollectDao
import dev.aplika.core.database.model.CollectPointEntity
import dev.aplika.core.database.model.CollectEntity

@Database(
    entities = [
        CollectPointEntity::class,
        CollectEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class BathabilityDatabase : RoomDatabase() {
    abstract fun collectPointDetailedDao(): CollectPointDetailedDao
    abstract fun collectPointDao(): CollectPointDao
    abstract fun collectDao(): CollectDao
}