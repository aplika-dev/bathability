package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.BathabilitySituation
import dev.aplika.core.domain.model.MonitoringPoint
import dev.aplika.core.network.model.BahiaMonitoringPointDto
import javax.inject.Inject

class BahiaMonitoringPointDtoToMonitoringPointMapper @Inject constructor() : Mapper<BahiaMonitoringPointDto, MonitoringPoint> {
    override fun map(input: BahiaMonitoringPointDto): MonitoringPoint {
        return MonitoringPoint(
            id = input.id.toString(),
            latitude = input.latLng[0].toDouble(),
            longitude = input.latLng[1].toDouble(),
            bathabilitySituation = when (input.category) {
                1 -> BathabilitySituation.INAPPROPRIATE
                2 -> BathabilitySituation.APPROPRIATE
                else -> BathabilitySituation.UNDETERMINED
            }
        )
    }
}