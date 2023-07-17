package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.MonitoringPointWithCollectsEntity
import dev.aplika.core.domain.model.MonitoringPoint
import javax.inject.Inject

class MonitoringPointWithCollectsEntityToMonitoringPointMapper @Inject constructor(
    private val monitoringPointCollectEntityToMonitoringPointCollectMapper: MonitoringPointCollectEntityToMonitoringPointCollectMapper
) : Mapper<MonitoringPointWithCollectsEntity, MonitoringPoint> {
    override fun map(input: MonitoringPointWithCollectsEntity): MonitoringPoint {
        return MonitoringPoint(
            id = input.beach.id,
            cityIbgeId = input.beach.cityIbgeId,
            city = input.beach.city,
            collectPoint = input.beach.collectPoint,
            beach = input.beach.beach,
            location = input.beach.location,
            latitude = input.beach.latitude,
            longitude = input.beach.longitude,
            monitoringPointCollects = input.collects.map { monitoringPointCollectEntityToMonitoringPointCollectMapper.map(input = it) }.sortedByDescending { it.date }
        )
    }
}