package dev.aplika.core.data.datasource

import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.android.di.IoDispatcher
import dev.aplika.core.data.mapper.MonitoringPointEntityToMonitoringPointMapper
import dev.aplika.core.data.mapper.MonitoringPointToMonitoringPointEntityMapper
import dev.aplika.core.database.dao.MonitoringPointDao
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
    private val monitoringPointDao: MonitoringPointDao,
    private val monitoringPointEntityToMonitoringPointMapper: MonitoringPointEntityToMonitoringPointMapper,
    private val monitoringPointToMonitoringPointEntityMapper: MonitoringPointToMonitoringPointEntityMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    fun getAll(): Flow<List<MonitoringPoint>> {
        return monitoringPointDao.getAll()
            .flowOn(ioDispatcher)
            .map { list -> list.map { monitoringPointEntityToMonitoringPointMapper.map(input = it) } }
            .flowOn(defaultDispatcher)
    }

    suspend fun insertAll(monitoringPoints: List<MonitoringPoint>) {
        withContext(ioDispatcher) {
            monitoringPointDao.insertAll(
                list = withContext(defaultDispatcher) {
                    monitoringPoints.map { monitoringPointToMonitoringPointEntityMapper.map(input = it) }
                }
            )
        }
    }

}