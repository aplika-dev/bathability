package dev.aplika.data.collect.model

import dev.aplika.core.domain.model.Collect
import dev.aplika.core.domain.model.LocalityGroup

data class CollectWithIdAndLocalityGroup(
    val collect: Collect,
    val collectPointId: String,
    val localityGroup: LocalityGroup
)
