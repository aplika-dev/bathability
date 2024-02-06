package dev.aplika.data.collect.model

import dev.aplika.core.domain.model.Collect
import dev.aplika.core.domain.model.CollectPoint

data class CollectWithCollectPoint(
    val collect: Collect,
    val collectPoint: CollectPoint
)
