package dev.aplika.feature.map.model

import dev.aplika.core.domain.model.CollectPoint

data class MapUIStateMapperArgs(
    val collectPoints: List<CollectPoint>,
    val shouldShowInAppReview: Boolean
)
