package dev.aplika.core.domain.repository

import dev.aplika.core.domain.model.Collect
import dev.aplika.core.domain.model.LocalityGroup
import kotlinx.coroutines.flow.Flow

interface CollectRepository {

    fun getByIdAndLocalityGroup(id: String, localityGroup: LocalityGroup): Flow<List<Collect>>
    fun insertAll(items: List<Collect>, collectPointId: String, localityGroup: LocalityGroup): Flow<Unit>

}