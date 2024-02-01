package dev.aplika.collect_point_detailed.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.data.mapper.CollectPointIdEntityToCollectPointIdMapper
import dev.aplika.core.database.model.CollectPointDetailedWithCollectsEntity
import dev.aplika.core.domain.model.CollectPointDetailed
import javax.inject.Inject

class CollectPointDetailedWithCollectsEntityToCollectPointDetailedMapper @Inject constructor(
    private val collectPointIdEntityToCollectPointIdMapper: CollectPointIdEntityToCollectPointIdMapper,
    private val collectEntityToCollectMapper: CollectEntityToCollectMapper
) : Mapper<CollectPointDetailedWithCollectsEntity, CollectPointDetailed> {
    override fun map(input: CollectPointDetailedWithCollectsEntity): CollectPointDetailed {
        return CollectPointDetailed(
            id = collectPointIdEntityToCollectPointIdMapper.map(input = input.collectPointDetailed.id),
            name = input.collectPointDetailed.name,
            city = input.collectPointDetailed.city,
            latitude = input.collectPointDetailed.latitude,
            longitude = input.collectPointDetailed.longitude,
            latestCollects = input.collects.map { collectEntityToCollectMapper.map(input = it) }
        )
    }
}