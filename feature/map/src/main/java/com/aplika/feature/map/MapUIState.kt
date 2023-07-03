package com.aplika.feature.map

import com.aplika.feature.map.state.MarkerState

data class MapUIState(
    val locationList: List<MarkerState> = emptyList()
)
