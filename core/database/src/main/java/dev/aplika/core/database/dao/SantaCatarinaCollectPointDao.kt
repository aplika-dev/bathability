package dev.aplika.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import dev.aplika.core.database.model.SantaCatarinaCollectPointEntity

@Dao
interface SantaCatarinaCollectPointDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<SantaCatarinaCollectPointEntity>)

}