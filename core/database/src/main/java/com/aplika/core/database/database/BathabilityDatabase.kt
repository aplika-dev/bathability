package com.aplika.core.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aplika.core.database.dao.BeachDao
import com.aplika.core.database.dao.CityDao
import com.aplika.core.database.dao.LocationDao
import com.aplika.core.database.model.BeachEntity
import com.aplika.core.database.model.CityEntity
import com.aplika.core.database.model.LocationEntity

@Database(
    entities = [
        CityEntity::class,
        BeachEntity::class,
        LocationEntity::class
    ],
    version = 1
)
abstract class BathabilityDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun beachDao(): BeachDao
    abstract fun locationDao(): LocationDao
}