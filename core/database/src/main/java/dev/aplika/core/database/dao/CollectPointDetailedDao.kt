package dev.aplika.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import dev.aplika.core.database.model.CollectPointDetailedEntity
import dev.aplika.core.domain.model.LocalityGroup
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectPointDetailedDao {

    @Upsert
    suspend fun insert(item: CollectPointDetailedEntity)

    @Query("SELECT * FROM `collect_point_detailed` WHERE id = :id AND locality_group = :localityGroup")
    fun getCollectPointDetailedById(id: String, localityGroup: LocalityGroup): Flow<CollectPointDetailedEntity?>

}