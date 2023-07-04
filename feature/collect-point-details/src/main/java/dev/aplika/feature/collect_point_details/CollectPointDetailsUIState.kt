package dev.aplika.feature.collect_point_details

import dev.aplika.core.kotlin.extensions.EMPTY
import dev.aplika.feature.collect_point_details.state.CollectState

data class CollectPointDetailsUIState(
    val isLoading: Boolean = true,
    val title: String = String.EMPTY,
    val subtitle: String = String.EMPTY,
    val description: String = String.EMPTY,
    val collects: List<CollectState> = emptyList()
)
