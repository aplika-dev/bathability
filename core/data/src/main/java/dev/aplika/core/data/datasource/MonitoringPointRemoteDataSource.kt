package dev.aplika.core.data.datasource

import dev.aplika.core.android.di.annotation.DefaultDispatcher
import dev.aplika.core.android.di.annotation.IoDispatcher
import dev.aplika.core.data.mapper.BrBaMonitoringPointDtoToMonitoringPointMapper
import dev.aplika.core.data.mapper.BrScMonitoringPointDtoToMonitoringPointMapper
import dev.aplika.core.domain.model.MonitoringPoint
import dev.aplika.core.network.service.BrBaService
import dev.aplika.core.network.service.BrScService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MonitoringPointRemoteDataSource @Inject constructor(
    private val brScService: BrScService,
    private val brBaService: BrBaService,
    private val brScMonitoringPointDtoToMonitoringPointMapper: BrScMonitoringPointDtoToMonitoringPointMapper,
    private val brBaMonitoringPointDtoToMonitoringPointMapper: BrBaMonitoringPointDtoToMonitoringPointMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    suspend fun getAll(): List<MonitoringPoint> {
        return coroutineScope {
            listOf(
                async { getBrScMonitoringPoints() },
                async { getBrBaMonitoringPoints() }
            ).awaitAll().flatten()
        }
    }

    private suspend fun getBrScMonitoringPoints(): List<MonitoringPoint> {
        return withContext(defaultDispatcher) {
            withContext(ioDispatcher) { brScService.getBeachCollects() }
                .map { brScMonitoringPointDtoToMonitoringPointMapper.map(input = it) }
        }
    }

    private suspend fun getBrBaMonitoringPoints(): List<MonitoringPoint> {
        return withContext(defaultDispatcher) {
            withContext(ioDispatcher) { brBaService.getBeachCollects() }
                .map { brBaMonitoringPointDtoToMonitoringPointMapper.map(input = it) }
        }
    }

}