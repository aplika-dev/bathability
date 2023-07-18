package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.MonitoringPointEntity
import dev.aplika.core.domain.model.MonitoringPoint
import javax.inject.Inject

class MonitoringPointToMonitoringPointEntityMapper @Inject constructor() : Mapper<MonitoringPoint, MonitoringPointEntity> {
    override fun map(input: MonitoringPoint): MonitoringPointEntity {
        return MonitoringPointEntity(
            id = input.id,
            latitude = input.latitude,
            longitude = input.longitude,
            bathabilitySituation = input.bathabilitySituation
        )
    }
}