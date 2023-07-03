package com.aplika.core.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aplika.core.database.dao.BeachDao
import com.aplika.core.database.dao.BeachWithCollectsDao
import com.aplika.core.database.dao.CollectDao
import com.aplika.core.database.model.BeachEntity
import com.aplika.core.database.model.CollectEntity

@Database(
    entities = [
        BeachEntity::class,
        CollectEntity::class
    ],
    version = 3, // TODO: reduce here to 1 before launch
    exportSchema = false
)
abstract class BathabilityDatabase : RoomDatabase() {
    abstract fun beachCollectDao(): BeachWithCollectsDao
    abstract fun beachDao(): BeachDao
    abstract fun collectDao(): CollectDao
}