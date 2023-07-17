package dev.aplika.core.domain.repository

import dev.aplika.core.domain.model.MonitoringPoint
import kotlinx.coroutines.flow.Flow

interface MonitoringPointRepository {

    fun getAll(): Flow<List<MonitoringPoint>>
    fun getById(id: String): Flow<MonitoringPoint>
    fun sync(): Flow<Unit>

}