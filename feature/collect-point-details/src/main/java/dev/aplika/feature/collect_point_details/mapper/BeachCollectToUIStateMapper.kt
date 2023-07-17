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
        return CollectPointDetailsUIState(
            isLoading = false,
            title = input.beach,
            subtitle = input.city,
            description = "${input.location} (${input.collectPoint})",
            collects = input.monitoringPointCollects.map {
                CollectState(
                    isLoading = false,
                    leadingIcon = when (it.bathabilitySituation) {
                        BathabilitySituation.APPROPRIATE -> R.drawable.ic_swim
                        BathabilitySituation.INAPPROPRIATE -> R.drawable.ic_forbidden
                        BathabilitySituation.UNDETERMINED -> R.drawable.ic_unknown
                    },
                    leadingContentDescription = when (it.bathabilitySituation) {
                        BathabilitySituation.APPROPRIATE -> R.string.ic_swim_content_description
                        BathabilitySituation.INAPPROPRIATE -> R.string.ic_forbidden_content_description
                        BathabilitySituation.UNDETERMINED -> R.string.ic_unknown_content_description
                    },
                    headline = it.date.formatToString(format = "dd/MM/yyyy"),
                    supportingResId = when (it.rainSituation) {
                        RainSituation.ABSENT -> R.string.no_rain
                        RainSituation.WEAK -> R.string.weak_rain
                        RainSituation.MODERATE -> R.string.moderate_rain
                        RainSituation.UNKNOWN -> R.string.no_information
                    },
                    trailingContent = it.escherichiaColiFactor
                )
            }
        )
    }
}