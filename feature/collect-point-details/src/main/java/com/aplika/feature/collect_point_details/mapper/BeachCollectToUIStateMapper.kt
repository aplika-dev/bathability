package com.aplika.feature.collect_point_details.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.domain.model.BathabilitySituation
import com.aplika.core.domain.model.BeachCollect
import com.aplika.core.domain.model.RainSituation
import com.aplika.core.kotlin.extensions.formatToString
import com.aplika.core.resources.R
import com.aplika.feature.collect_point_details.CollectPointDetailsUIState
import com.aplika.feature.collect_point_details.state.CollectState
import javax.inject.Inject

class BeachCollectToUIStateMapper @Inject constructor() : Mapper<BeachCollect, CollectPointDetailsUIState> {
    override fun map(input: BeachCollect): CollectPointDetailsUIState {
        return CollectPointDetailsUIState(
            isLoading = false,
            title = input.beach,
            subtitle = input.city,
            description = "${input.location} (${input.collectPoint})",
            collects = input.collects.map {
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