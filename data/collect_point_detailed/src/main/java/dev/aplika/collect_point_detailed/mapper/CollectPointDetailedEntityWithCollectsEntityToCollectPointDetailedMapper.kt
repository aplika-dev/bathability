package dev.aplika.collect_point_detailed.mapper

import dev.aplika.collect_point_detailed.model.CollectPointDetailedEntityWithCollectsEntity
import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.CollectPointDetailed
import javax.inject.Inject

class CollectPointDetailedEntityWithCollectsEntityToCollectPointDetailedMapper @Inject constructor(
    private val collectEntityToCollectMapper: CollectEntityToCollectMapper
) : Mapper<CollectPointDetailedEntityWithCollectsEntity, CollectPointDetailed> {
    override fun map(input: CollectPointDetailedEntityWithCollectsEntity): CollectPointDetailed {
        return CollectPointDetailed(
            id = input.collectPointDetailed.id,
            localityGroup = input.collectPointDetailed.localityGroup,
            name = input.collectPointDetailed.name,
            city = input.collectPointDetailed.city,
            latitude = input.collectPointDetailed.latitude,
            longitude = input.collectPointDetailed.longitude,
            latestCollects = input.collects.map { collectEntityToCollectMapper.map(input = it) }
        )
    }
}