package com.aplika.feature.map

import com.aplika.core.domain.model.Location

data class MapUIState(
    val locationList: List<Location> = emptyList()
)
