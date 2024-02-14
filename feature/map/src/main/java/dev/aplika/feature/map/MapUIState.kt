package dev.aplika.feature.map

import dev.aplika.feature.map.state.MarkerState

data class MapUIState(
    val isLoading: Boolean = true,
    val locationList: List<MarkerState> = emptyList(),
    val shouldShowInAppReview: Boolean = false
)
