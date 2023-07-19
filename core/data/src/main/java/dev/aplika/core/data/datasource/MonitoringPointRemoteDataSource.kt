package dev.aplika.core.data.datasource

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.android.di.IoDispatcher
import dev.aplika.core.domain.model.MonitoringPoint
import dev.aplika.datasource.bahia.mapper.GetDetailsResponseDtoToMonitoringPointsMapper
import dev.aplika.datasource.bahia.service.BahiaService
import dev.aplika.datasource.santacatarina.mapper.MonitoringPointDtoToMonitoringPointMapper
import dev.aplika.datasource.santacatarina.service.SantaCatarinaService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MonitoringPointRemoteDataSource @Inject constructor(
    private val santaCatarinaService: SantaCatarinaService,
    private val bahiaService: BahiaService,
    private val monitoringPointDtoToMonitoringPointMapper: MonitoringPointDtoToMonitoringPointMapper,
    private val getDetailsResponseDtoToMonitoringPointsMapper: GetDetailsResponseDtoToMonitoringPointsMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    fun getAll(): Flow<List<MonitoringPoint>> {
        return merge(
            getBahiaMonitoringPoints(),
            getSantaCatarinaMonitoringPoints()
        )
    }

    private fun getBahiaMonitoringPoints(): Flow<List<MonitoringPoint>> {
        return flow { emit(bahiaService.getDetails(authorization = "")) }
            .flowOn(ioDispatcher)
            .map { getDetailsResponseDtoToMonitoringPointsMapper.map(input = it) }
            .flowOn(defaultDispatcher)
    }

    private fun getSantaCatarinaMonitoringPoints(): Flow<List<MonitoringPoint>> {
        return flow { emit(santaCatarinaService.getMonitoringPoints()) }
            .flowOn(ioDispatcher)
            .map { points -> points.map { monitoringPointDtoToMonitoringPointMapper.map(input = it) } }
            .flowOn(defaultDispatcher)
    }

}