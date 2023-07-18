package dev.aplika.core.data.datasource

import dev.aplika.core.android.di.annotation.DefaultDispatcher
import dev.aplika.core.android.di.annotation.IoDispatcher
import dev.aplika.core.data.mapper.BahiaMonitoringPointDtoToMonitoringPointMapper
import dev.aplika.core.data.mapper.SantaCatarinaMonitoringPointDtoToMonitoringPointMapper
import dev.aplika.core.domain.model.MonitoringPoint
import dev.aplika.core.network.service.BahiaService
import dev.aplika.core.network.service.SantaCatarinaService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MonitoringPointRemoteDataSource @Inject constructor(
    private val santaCatarinaService: SantaCatarinaService,
    private val bahiaService: BahiaService,
    private val santaCatarinaMonitoringPointDtoToMonitoringPointMapper: SantaCatarinaMonitoringPointDtoToMonitoringPointMapper,
    private val bahiaMonitoringPointDtoToMonitoringPointMapper: BahiaMonitoringPointDtoToMonitoringPointMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    suspend fun getAll(): List<MonitoringPoint> {
        return coroutineScope {
            listOf(
                async { getSantaCatarinaMonitoringPoints() },
                async { getBahiaMonitoringPoints() }
            ).awaitAll().flatten()
        }
    }

    private suspend fun getSantaCatarinaMonitoringPoints(): List<MonitoringPoint> {
        return withContext(defaultDispatcher) {
            withContext(ioDispatcher) { santaCatarinaService.getBeachCollects() }
                .map { santaCatarinaMonitoringPointDtoToMonitoringPointMapper.map(input = it) }
        }
    }

    private suspend fun getBahiaMonitoringPoints(): List<MonitoringPoint> {
        return withContext(defaultDispatcher) {
            withContext(ioDispatcher) { bahiaService.getBeachCollects() }
                .map { bahiaMonitoringPointDtoToMonitoringPointMapper.map(input = it) }
        }
    }

}