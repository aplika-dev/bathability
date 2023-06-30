package com.aplika.feature.map

import com.aplika.feature.map.presentation.LocationPresentation

data class MapUIState(
    val locationList: List<LocationPresentation> = emptyList()
)
