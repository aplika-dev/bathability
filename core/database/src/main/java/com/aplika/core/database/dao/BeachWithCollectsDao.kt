package com.aplika.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.aplika.core.database.model.BeachWithCollectsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BeachWithCollectsDao {

    @Transaction
    @Query("SELECT * FROM `beach`")
    fun getAll(): Flow<List<BeachWithCollectsEntity>>

}