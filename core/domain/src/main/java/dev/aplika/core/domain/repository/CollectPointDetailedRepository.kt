package dev.aplika.core.domain.repository

import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.core.domain.model.CollectPointDetailed
import dev.aplika.core.domain.model.CollectPointId
import kotlinx.coroutines.flow.Flow

interface CollectPointDetailedRepository {

    fun getById(id: CollectPointId): Flow<CollectPointDetailed>

}