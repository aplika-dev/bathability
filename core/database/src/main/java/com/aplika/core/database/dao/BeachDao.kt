package com.aplika.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aplika.core.database.model.BeachEntity
import com.aplika.core.database.model.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BeachDao {

    @Query("SELECT * FROM `beach` WHERE city_id = :cityId")
    fun getByCityId(cityId: String): Flow<List<BeachEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<BeachEntity>)

}