package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.MonitoringPointEntity
import dev.aplika.core.domain.model.MonitoringPoint
import javax.inject.Inject

class MonitoringPointEntityToMonitoringPointMapper @Inject constructor() : Mapper<MonitoringPointEntity, MonitoringPoint> {
    override fun map(input: MonitoringPointEntity): MonitoringPoint {
        return MonitoringPoint(
            id = input.id,
            latitude = input.latitude,
            longitude = input.longitude,
            bathabilitySituation = input.bathabilitySituation
        )
    }
}