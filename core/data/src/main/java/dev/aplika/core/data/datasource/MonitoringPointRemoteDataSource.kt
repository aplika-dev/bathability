package dev.aplika.core.data.datasource

import dev.aplika.core.android.di.annotation.DefaultDispatcher
import dev.aplika.core.android.di.annotation.IoDispatcher
import dev.aplika.core.data.mapper.BrScMonitoringPointDtoToMonitoringPointMapper
import dev.aplika.core.domain.model.MonitoringPoint
import dev.aplika.core.network.service.BrScService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MonitoringPointRemoteDataSource @Inject constructor(
    private val brScService: BrScService,
    private val brScMonitoringPointDtoToMonitoringPointMapper: BrScMonitoringPointDtoToMonitoringPointMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    suspend fun getAll(): List<MonitoringPoint> {
        val cityListDto = withContext(ioDispatcher) {
            brScService.getBeachCollects()
        }

        return withContext(defaultDispatcher) {
            cityListDto.map { brScMonitoringPointDtoToMonitoringPointMapper.map(input = it) }
        }
    }

}