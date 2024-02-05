package dev.aplika.feature.collect_point_detailed.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.BathabilityStatus
import dev.aplika.core.domain.model.CollectPointWithCollects
import dev.aplika.core.domain.model.RainStatus
import dev.aplika.core.kotlin.extensions.formatToString
import dev.aplika.core.resources.R
import dev.aplika.core.ui.model.Task
import dev.aplika.feature.collect_point_detailed.CollectPointDetailedUIState
import dev.aplika.feature.collect_point_detailed.state.CollectState
import dev.aplika.feature.collect_point_detailed.state.HeaderState
import javax.inject.Inject

class CollectPointWithCollectsTaskToUIStateMapper @Inject constructor() : Mapper<Task<CollectPointWithCollects?>, CollectPointDetailedUIState> {
    override fun map(input: Task<CollectPointWithCollects?>): CollectPointDetailedUIState {
        return when (input) {
            is Task.Error -> CollectPointDetailedUIState.IsError
            Task.Loading -> CollectPointDetailedUIState.HasContent(
                headerState = HeaderState.IsLoading,
                collects = listOf(
                    CollectState.IsLoading,
                    CollectState.IsLoading,
                    CollectState.IsLoading
                ),
                shouldShowInAppReview = false
            )
            is Task.Success -> input.data?.mapSuccess() ?: CollectPointDetailedUIState.IsError
        }
    }

    private fun CollectPointWithCollects.mapSuccess(): CollectPointDetailedUIState.HasContent {
        return CollectPointDetailedUIState.HasContent(
            headerState = HeaderState.HasContent(
                title = collectPoint.name,
                subtitle = collectPoint.city
            ),
            collects = collects.map {
                CollectState.HasContent(
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
                        RainStatus.UNKNOWN -> null
                        null -> null
                    },
                    trailingContent = it.escherichiaColiFactor
                )
            },
            shouldShowInAppReview = true
        )
    }
}