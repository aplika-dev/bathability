package dev.aplika.feature.collect_point_details.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaBathabilityStatus
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaCollectPoint
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaRainStatus
import dev.aplika.core.kotlin.extensions.formatToString
import dev.aplika.core.resources.R
import dev.aplika.feature.collect_point_details.CollectPointDetailsUIState
import dev.aplika.feature.collect_point_details.state.CollectState
import javax.inject.Inject

class SantaCatarinaCollectPointToUIStateMapper @Inject constructor() : Mapper<SantaCatarinaCollectPoint, CollectPointDetailsUIState> {
    override fun map(input: SantaCatarinaCollectPoint): CollectPointDetailsUIState {
        return CollectPointDetailsUIState(
            isLoading = false,
            title = input.name,
            subtitle = input.city,
            description = input.description,
            collects = input.collects.map {
                CollectState(
                    isLoading = false,
                    leadingIcon = when (it.bathabilityStatus) {
                        SantaCatarinaBathabilityStatus.APPROPRIATE -> R.drawable.ic_swim
                        SantaCatarinaBathabilityStatus.INAPPROPRIATE -> R.drawable.ic_forbidden
                        SantaCatarinaBathabilityStatus.UNDETERMINED -> R.drawable.ic_unknown
                    },
                    leadingContentDescription = when (it.bathabilityStatus) {
                        SantaCatarinaBathabilityStatus.APPROPRIATE -> R.string.ic_swim_content_description
                        SantaCatarinaBathabilityStatus.INAPPROPRIATE -> R.string.ic_forbidden_content_description
                        SantaCatarinaBathabilityStatus.UNDETERMINED -> R.string.ic_unknown_content_description
                    },
                    headline = it.date.formatToString(format = "dd/MM/yyyy"),
                    supportingResId = when (it.rainStatus) {
                        SantaCatarinaRainStatus.ABSENT -> R.string.no_rain
                        SantaCatarinaRainStatus.WEAK -> R.string.weak_rain
                        SantaCatarinaRainStatus.MODERATE -> R.string.moderate_rain
                        SantaCatarinaRainStatus.UNKNOWN -> R.string.no_information
                    },
                    trailingContent = it.escherichiaColiFactor
                )
            }
        )
    }
}