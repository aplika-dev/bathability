package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.BathabilitySituation
import dev.aplika.core.domain.model.MonitoringPoint
import dev.aplika.core.network.model.BrBaMonitoringPointDto
import dev.aplika.core.network.model.BrScMonitoringPointDto
import javax.inject.Inject

class BrBaMonitoringPointDtoToMonitoringPointMapper @Inject constructor() : Mapper<BrBaMonitoringPointDto, MonitoringPoint> {
    override fun map(input: BrBaMonitoringPointDto): MonitoringPoint {
        return MonitoringPoint(
            id = input.id.toString(),
            latitude = input.latLng[0].toDouble(),
            longitude = input.latLng[1].toDouble(),
            bathabilitySituation = when (input.category) {
                1 -> BathabilitySituation.APPROPRIATE
                2 -> BathabilitySituation.INAPPROPRIATE
                else -> BathabilitySituation.UNDETERMINED
            }
        )
    }
}