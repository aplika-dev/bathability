package dev.aplika.core.data.datasource

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.android.di.IoDispatcher
import dev.aplika.core.data.mapper.MonitoringPointDtoToMonitoringPointMapper
import dev.aplika.core.domain.model.MonitoringPoint
import dev.aplika.datasource.santacatarina.service.SantaCatarinaService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MonitoringPointRemoteDataSource @Inject constructor(
    private val santaCatarinaService: SantaCatarinaService,
    private val monitoringPointDtoToMonitoringPointMapper: MonitoringPointDtoToMonitoringPointMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    suspend fun getAll(): List<MonitoringPoint> {
        return withContext(defaultDispatcher) {
            withContext(ioDispatcher) { santaCatarinaService.getMonitoringPoints() }
                .map { monitoringPointDtoToMonitoringPointMapper.map(input = it) }
        }
    }

}