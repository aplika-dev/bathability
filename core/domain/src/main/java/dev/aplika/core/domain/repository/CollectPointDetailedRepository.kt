package dev.aplika.core.domain.repository

import dev.aplika.core.domain.model.CollectPointDetailed
import dev.aplika.core.domain.model.LocalityGroup
import kotlinx.coroutines.flow.Flow

interface CollectPointDetailedRepository {

    fun getByIdAndLocalityGroup(id: String, localityGroup: LocalityGroup): Flow<CollectPointDetailed>
    fun insertAll(items: List<CollectPointDetailed>): Flow<Unit>

}