package com.aplika.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aplika.core.database.model.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Query("SELECT * FROM `city`")
    fun getAll(): Flow<List<CityEntity>>

    @Query("SELECT * FROM `city` WHERE id = :id")
    fun getById(id: String): Flow<CityEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<CityEntity>)

    @Query("DELETE FROM `city`")
    suspend fun clear()

}