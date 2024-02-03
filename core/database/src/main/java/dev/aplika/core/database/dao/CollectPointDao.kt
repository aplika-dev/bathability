package dev.aplika.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import dev.aplika.core.database.model.CollectPointEntity
import dev.aplika.core.domain.model.LocalityGroup
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectPointDao {

    @Upsert
    suspend fun insertAll(list: List<CollectPointEntity>)

    @Query("SELECT * FROM `collect_point`")
    fun getAll(): Flow<List<CollectPointEntity>>

    @Query("SELECT * FROM `collect_point` WHERE id = :id AND locality_group = :localityGroup")
    fun getByIdAndLocalityGroup(id: String, localityGroup: LocalityGroup): Flow<CollectPointEntity?>

}