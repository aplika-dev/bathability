package dev.aplika.core.domain.repository

import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaCollectPoint
import kotlinx.coroutines.flow.Flow

interface SantaCatarinaCollectPointRepository {

    fun getAll(): Flow<List<SantaCatarinaCollectPoint>>
    fun getById(id: String): Flow<SantaCatarinaCollectPoint>
    fun fetch(): Flow<Unit>

}