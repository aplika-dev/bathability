package dev.aplika.feature.collect_point_details.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.BathabilityStatus
import dev.aplika.core.domain.model.CollectPointDetailed
import dev.aplika.core.domain.model.RainStatus
import dev.aplika.core.kotlin.extensions.formatToString
import dev.aplika.core.resources.R
import dev.aplika.feature.collect_point_details.CollectPointDetailsUIState
import dev.aplika.feature.collect_point_details.state.CollectState
import javax.inject.Inject

class CollectPointDetailedToUIStateMapper @Inject constructor() : Mapper<CollectPointDetailed, CollectPointDetailsUIState> {
    override fun map(input: CollectPointDetailed): CollectPointDetailsUIState {
        return CollectPointDetailsUIState(
            isLoading = false,
            title = input.name,
            subtitle = input.city,
            collects = input.latestCollects.map {
                CollectState(
                    isLoading = false,
                    leadingIcon = when (it.bathabilityStatus) {
                        BathabilityStatus.APPROPRIATE -> R.drawable.ic_swim
                        BathabilityStatus.INAPPROPRIATE -> R.drawable.ic_forbidden
                        BathabilityStatus.UNDETERMINED -> R.drawable.ic_unknown
                    },
                    leadingContentDescription = when (it.bathabilityStatus) {
                        BathabilityStatus.APPROPRIATE -> R.string.ic_swim_content_description
                        BathabilityStatus.INAPPROPRIATE -> R.string.ic_forbidden_content_description
                        BathabilityStatus.UNDETERMINED -> R.string.ic_unknown_content_description
                    },
                    headline = it.date.formatToString(format = "dd/MM/yyyy"),
                    supportingResId = when (it.rainStatus) {
                        RainStatus.ABSENT -> R.string.no_rain
                        RainStatus.WEAK -> R.string.weak_rain
                        RainStatus.MODERATE -> R.string.moderate_rain
                        RainStatus.UNKNOWN -> R.string.no_information
                        null -> R.string.no_information
                    },
                    trailingContent = it.escherichiaColiFactor
                )
            }
        )
    }
}