package dev.aplika.core.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.aplika.core.database.dao.BeachDao
import dev.aplika.core.database.dao.BeachWithCollectsDao
import dev.aplika.core.database.dao.CollectDao
import dev.aplika.core.database.model.BeachEntity
import dev.aplika.core.database.model.CollectEntity

@Database(
    entities = [
        BeachEntity::class,
        CollectEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BathabilityDatabase : RoomDatabase() {
    abstract fun beachCollectDao(): BeachWithCollectsDao
    abstract fun beachDao(): BeachDao
    abstract fun collectDao(): CollectDao
}