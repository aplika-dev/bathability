package com.aplika.feature.collect_point_details

import com.aplika.core.kotlin.extensions.EMPTY
import com.aplika.feature.collect_point_details.state.CollectState

data class CollectPointDetailsState(
    val isLoading: Boolean = true,
    val title: String = String.EMPTY,
    val description: String = String.EMPTY,
    val collects: List<CollectState> = emptyList()
)
