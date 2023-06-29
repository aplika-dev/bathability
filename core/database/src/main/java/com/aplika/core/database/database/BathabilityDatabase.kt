package com.aplika.core.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aplika.core.database.dao.CityDao
import com.aplika.core.database.model.CityEntity

@Database(
    entities = [
        CityEntity::class
    ],
    version = 1
)
abstract class BathabilityDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}