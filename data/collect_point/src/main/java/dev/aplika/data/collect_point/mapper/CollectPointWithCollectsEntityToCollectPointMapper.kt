package dev.aplika.data.collect_point.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.CollectPointWithCollectsEntity
import dev.aplika.core.domain.model.CollectPoint
import javax.inject.Inject

class CollectPointWithCollectsEntityToCollectPointMapper @Inject constructor(
    private val collectEntityToCollectMapper: CollectEntityToCollectMapper
) : Mapper<CollectPointWithCollectsEntity, CollectPoint> {
    override fun map(input: CollectPointWithCollectsEntity): CollectPoint {
        return CollectPoint(
            id = input.collectPoint.id,
            city = input.collectPoint.city,
            name = input.collectPoint.name,
            description = input.collectPoint.description,
            latitude = input.collectPoint.latitude,
            longitude = input.collectPoint.longitude,
            collects = input.collects.map { collectEntityToCollectMapper.map(input = it) }.sortedByDescending { it.date }
        )
    }
}