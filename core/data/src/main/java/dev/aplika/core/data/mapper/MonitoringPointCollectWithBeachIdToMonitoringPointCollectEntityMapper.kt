package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.MonitoringPointCollectEntity
import dev.aplika.core.domain.model.MonitoringPointCollect
import javax.inject.Inject

class MonitoringPointCollectWithBeachIdToMonitoringPointCollectEntityMapper @Inject constructor() : Mapper<Pair<MonitoringPointCollect, String>, MonitoringPointCollectEntity> {
    override fun map(input: Pair<MonitoringPointCollect, String>): MonitoringPointCollectEntity {
        return MonitoringPointCollectEntity(
            date = input.first.date.time,
            bathabilitySituation = input.first.bathabilitySituation.id,
            rainSituation = input.first.rainSituation.id,
            escherichiaColiFactor = input.first.escherichiaColiFactor,
            beachId = input.second
        )
    }
}