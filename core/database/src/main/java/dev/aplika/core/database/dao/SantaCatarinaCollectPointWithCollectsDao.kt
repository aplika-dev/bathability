package dev.aplika.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import dev.aplika.core.database.model.SantaCatarinaCollectPointWithCollectsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SantaCatarinaCollectPointWithCollectsDao {

    @Transaction
    @Query("SELECT * FROM `santa_catarina_collect_point`")
    fun getAll(): Flow<List<SantaCatarinaCollectPointWithCollectsEntity>>

    @Transaction
    @Query("SELECT * FROM `santa_catarina_collect_point` WHERE id = :id")
    fun getById(id: String): Flow<SantaCatarinaCollectPointWithCollectsEntity>

}