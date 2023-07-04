package dev.aplika.core.domain.repository

import dev.aplika.core.domain.model.BeachCollect
import kotlinx.coroutines.flow.Flow

interface BeachCollectRepository {

    fun getAll(): Flow<List<BeachCollect>>
    fun getById(id: String): Flow<BeachCollect>
    fun sync(): Flow<Unit>

}