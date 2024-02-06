package dev.aplika.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import dev.aplika.core.database.model.CollectEntity
import dev.aplika.core.domain.model.LocalityGroup
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectDao {

    @Upsert
    suspend fun insertAll(list: List<CollectEntity>)

    @Query("SELECT * FROM `collect` WHERE collect_point_id = :id AND locality_group = :localityGroup")
    fun getAllByCollectPointIdAndLocalityGroup(id: String, localityGroup: LocalityGroup): Flow<List<CollectEntity>>

}