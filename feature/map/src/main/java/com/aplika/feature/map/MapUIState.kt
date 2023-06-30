package com.aplika.feature.map

import com.aplika.feature.map.presentation.MarkerPresentation

data class MapUIState(
    val locationList: List<MarkerPresentation> = emptyList()
)
