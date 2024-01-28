package dev.aplika.core.domain.repository

import dev.aplika.core.domain.model.CollectPoint
import kotlinx.coroutines.flow.Flow

interface BeachCollectRepository {

    fun getAll(): Flow<List<CollectPoint>>
    fun getById(id: String): Flow<CollectPoint>
    fun sync(): Flow<Unit>

}