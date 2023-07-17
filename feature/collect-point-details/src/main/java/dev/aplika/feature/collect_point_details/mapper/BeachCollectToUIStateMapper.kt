package dev.aplika.feature.collect_point_details.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.BathabilitySituation
import dev.aplika.core.domain.model.MonitoringPoint
import dev.aplika.core.domain.model.RainSituation
import dev.aplika.core.kotlin.extensions.formatToString
import dev.aplika.core.resources.R
import dev.aplika.feature.collect_point_details.CollectPointDetailsUIState
import dev.aplika.feature.collect_point_details.state.CollectState
import javax.inject.Inject

class BeachCollectToUIStateMapper @Inject constructor() : Mapper<MonitoringPoint, CollectPointDetailsUIState> {
    override fun map(input: MonitoringPoint): CollectPointDetailsUIState {
        TODO()
    }
}