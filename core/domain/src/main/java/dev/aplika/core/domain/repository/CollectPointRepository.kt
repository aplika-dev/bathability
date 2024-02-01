package dev.aplika.core.domain.repository

import dev.aplika.core.domain.model.CollectPoint
import kotlinx.coroutines.flow.Flow

interface CollectPointRepository {

    fun getAll(): Flow<List<CollectPoint>>
    fun fetch(): Flow<Unit>

}