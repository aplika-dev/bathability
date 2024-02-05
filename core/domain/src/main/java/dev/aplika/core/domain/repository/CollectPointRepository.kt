package dev.aplika.core.domain.repository

import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.core.domain.model.LocalityGroup
import kotlinx.coroutines.flow.Flow

interface CollectPointRepository {

    fun getAll(): Flow<List<CollectPoint>>
    fun getByIdAndLocalityGroup(id: String, localityGroup: LocalityGroup): Flow<CollectPoint?>
    suspend fun fetchAllCatching()

}