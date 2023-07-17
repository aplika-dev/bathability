package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.BathabilitySituation
import dev.aplika.core.domain.model.MonitoringPointCollect
import dev.aplika.core.domain.model.RainSituation
import dev.aplika.core.network.model.BrScMonitoringPointCollectDto
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class BrScMonitoringPointCollectDtoToMonitoringPointCollectMapper @Inject constructor() : Mapper<BrScMonitoringPointCollectDto, MonitoringPointCollect?> {
    override fun map(input: BrScMonitoringPointCollectDto): MonitoringPointCollect? {
        return MonitoringPointCollect(
            date = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR")).parse(input.date) ?: return null,
            bathabilitySituation = BathabilitySituation.getById(id = input.bathabilitySituation),
            rainSituation = RainSituation.getById(id = input.rainSituation),
            escherichiaColiFactor = input.escherichiaColiFactor.toInt()
        )
    }
}