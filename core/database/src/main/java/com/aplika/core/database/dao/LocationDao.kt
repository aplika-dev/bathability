package com.aplika.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aplika.core.database.model.BeachEntity
import com.aplika.core.database.model.CityEntity
import com.aplika.core.database.model.LocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    @Query("SELECT * FROM `location` WHERE beachId = :beachId")
    fun getByBeachId(beachId: String): Flow<List<LocationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<LocationEntity>)

}