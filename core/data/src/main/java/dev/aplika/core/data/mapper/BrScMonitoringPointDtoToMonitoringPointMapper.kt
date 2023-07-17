package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.BathabilitySituation
import dev.aplika.core.domain.model.MonitoringPoint
import dev.aplika.core.network.model.BrScMonitoringPointDto
import javax.inject.Inject

class BrScMonitoringPointDtoToMonitoringPointMapper @Inject constructor() : Mapper<BrScMonitoringPointDto, MonitoringPoint> {
    override fun map(input: BrScMonitoringPointDto): MonitoringPoint {
        return MonitoringPoint(
            id = input.cityId,
            latitude = input.latitude.toDouble(),
            longitude = input.longitude.toDouble(),
            bathabilitySituation = when (input.collects.firstOrNull()?.bathabilitySituation) {
                "PRÓPRIO" -> BathabilitySituation.APPROPRIATE
                "IMPRÓPRIO" -> BathabilitySituation.INAPPROPRIATE
                else -> BathabilitySituation.UNDETERMINED
            }
        )
    }
}