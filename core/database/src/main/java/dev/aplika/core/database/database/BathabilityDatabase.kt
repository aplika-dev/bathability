package dev.aplika.core.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.aplika.core.database.dao.SantaCatarinaCollectPointDao
import dev.aplika.core.database.dao.SantaCatarinaCollectPointWithCollectsDao
import dev.aplika.core.database.dao.SantaCatarinaCollectDao
import dev.aplika.core.database.model.SantaCatarinaCollectPointEntity
import dev.aplika.core.database.model.SantaCatarinaCollectEntity

@Database(
    entities = [
        SantaCatarinaCollectPointEntity::class,
        SantaCatarinaCollectEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class BathabilityDatabase : RoomDatabase() {
    abstract fun santaCatarinaCollectPointWithCollectsDao(): SantaCatarinaCollectPointWithCollectsDao
    abstract fun santaCatarinaCollectPointDao(): SantaCatarinaCollectPointDao
    abstract fun santaCatarinaCollectDao(): SantaCatarinaCollectDao
}