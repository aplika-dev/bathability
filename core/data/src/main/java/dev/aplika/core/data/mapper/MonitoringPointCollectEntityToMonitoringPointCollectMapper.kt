package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.MonitoringPointCollectEntity
import dev.aplika.core.domain.model.BathabilitySituation
import dev.aplika.core.domain.model.MonitoringPointCollect
import dev.aplika.core.domain.model.RainSituation
import java.util.Date
import javax.inject.Inject

class MonitoringPointCollectEntityToMonitoringPointCollectMapper @Inject constructor() : Mapper<MonitoringPointCollectEntity, MonitoringPointCollect> {
    override fun map(input: MonitoringPointCollectEntity): MonitoringPointCollect {
        return MonitoringPointCollect(
            date = Date(input.date),
            bathabilitySituation = BathabilitySituation.getById(id = input.bathabilitySituation),
            rainSituation = RainSituation.getById(id = input.rainSituation),
            escherichiaColiFactor = input.escherichiaColiFactor
        )
    }
}