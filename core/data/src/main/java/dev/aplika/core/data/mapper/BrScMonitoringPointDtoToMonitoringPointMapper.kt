package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.MonitoringPoint
import dev.aplika.core.network.model.BrScMonitoringPointDto
import javax.inject.Inject

class BrScMonitoringPointDtoToMonitoringPointMapper @Inject constructor(
    private val brScMonitoringPointCollectDtoToMonitoringPointCollectMapper: BrScMonitoringPointCollectDtoToMonitoringPointCollectMapper
) : Mapper<BrScMonitoringPointDto, MonitoringPoint> {
    override fun map(input: BrScMonitoringPointDto): MonitoringPoint {
        return MonitoringPoint(
            id = input.cityId,
            cityIbgeId = input.cityIbgeId,
            city = input.city,
            collectPoint = input.collectPoint,
            beach = input.beach,
            location = input.location,
            latitude = input.latitude.toDouble(),
            longitude = input.longitude.toDouble(),
            monitoringPointCollects = input.collects.mapNotNull { brScMonitoringPointCollectDtoToMonitoringPointCollectMapper.map(input = it) }
        )
    }
}