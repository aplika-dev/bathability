package dev.aplika.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import dev.aplika.core.database.model.BeachEntity
import dev.aplika.core.database.model.BeachWithCollectsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BeachDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<BeachEntity>)

}