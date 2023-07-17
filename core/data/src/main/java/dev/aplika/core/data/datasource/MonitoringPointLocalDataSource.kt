package dev.aplika.core.data.datasource

import dev.aplika.core.android.di.annotation.DefaultDispatcher
import dev.aplika.core.android.di.annotation.IoDispatcher
import dev.aplika.core.data.mapper.MonitoringPointToMonitoringPointEntityMapper
import dev.aplika.core.data.mapper.MonitoringPointWithCollectsEntityToMonitoringPointMapper
import dev.aplika.core.data.mapper.MonitoringPointCollectWithBeachIdToMonitoringPointCollectEntityMapper
import dev.aplika.core.database.dao.MonitoringPointDao
import dev.aplika.core.database.dao.MonitoringPointWithCollectsDao
import dev.aplika.core.database.dao.MonitoringPointCollectDao
import dev.aplika.core.domain.model.MonitoringPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MonitoringPointLocalDataSource @Inject constructor(
    private val monitoringPointWithCollectsDao: MonitoringPointWithCollectsDao,
    private val monitoringPointDao: MonitoringPointDao,
    private val monitoringPointCollectDao: MonitoringPointCollectDao,
    private val monitoringPointWithCollectsEntityToMonitoringPointMapper: MonitoringPointWithCollectsEntityToMonitoringPointMapper,
    private val monitoringPointCollectWithBeachIdToMonitoringPointCollectEntityMapper: MonitoringPointCollectWithBeachIdToMonitoringPointCollectEntityMapper,
    private val monitoringPointToMonitoringPointEntityMapper: MonitoringPointToMonitoringPointEntityMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    fun getAll(): Flow<List<MonitoringPoint>> {
        return monitoringPointWithCollectsDao.getAll()
            .flowOn(ioDispatcher)
            .map { list -> list.map { monitoringPointWithCollectsEntityToMonitoringPointMapper.map(input = it) } }
            .flowOn(defaultDispatcher)
    }

    fun getById(id: String): Flow<MonitoringPoint> {
        return monitoringPointWithCollectsDao.getById(id = id)
            .flowOn(ioDispatcher)
            .map { monitoringPointWithCollectsEntityToMonitoringPointMapper.map(input = it) }
            .flowOn(defaultDispatcher)
    }

    suspend fun insertAll(monitoringPoints: List<MonitoringPoint>) {
        val beachEntities = withContext(defaultDispatcher) {
            monitoringPoints.map { monitoringPointToMonitoringPointEntityMapper.map(input = it) }
        }

        withContext(ioDispatcher) {
            monitoringPointDao.insertAll(list = beachEntities)
        }

        val collectEntities = withContext(defaultDispatcher) {
            monitoringPoints.flatMap { beachCollect -> beachCollect.monitoringPointCollects.map { monitoringPointCollectWithBeachIdToMonitoringPointCollectEntityMapper.map(input = it to beachCollect.id) } }
        }

        withContext(ioDispatcher) {
            monitoringPointCollectDao.insertAll(list = collectEntities)
        }
    }

}