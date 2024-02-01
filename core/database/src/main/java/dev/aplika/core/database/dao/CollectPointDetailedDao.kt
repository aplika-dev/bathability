package dev.aplika.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import dev.aplika.core.database.model.CollectPointDetailedWithCollectsEntity
import dev.aplika.core.database.model.CollectPointEntity
import dev.aplika.core.database.model.CollectPointIdEntity
import dev.aplika.core.domain.model.CollectPointId
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectPointDetailedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CollectPointDetailedWithCollectsEntity)

    @Transaction
    @Query("SELECT * FROM `collect_point_detailed` WHERE id = :id")
    fun getById(id: CollectPointIdEntity): Flow<CollectPointDetailedWithCollectsEntity?>

}