package com.aplika.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.aplika.core.database.model.BeachEntity
import com.aplika.core.database.model.BeachWithCollectsEntity
import com.aplika.core.database.model.CollectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<CollectEntity>)

}