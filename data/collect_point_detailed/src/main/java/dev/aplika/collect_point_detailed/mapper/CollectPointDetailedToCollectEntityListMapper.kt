package dev.aplika.collect_point_detailed.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.CollectEntity
import dev.aplika.core.domain.model.CollectPointDetailed
import javax.inject.Inject

class CollectPointDetailedToCollectEntityListMapper @Inject constructor() : Mapper<CollectPointDetailed, List<CollectEntity>> {
    override fun map(input: CollectPointDetailed): List<CollectEntity> {
        return input.latestCollects
            .map { collect ->
                CollectEntity(
                    collectPointId = input.id,
                    localityGroup = input.localityGroup,
                    date = collect.date.time,
                    bathabilityStatus = collect.bathabilityStatus,
                    rainStatus = collect.rainStatus,
                    escherichiaColiFactor = collect.escherichiaColiFactor
                )
            }
    }
}