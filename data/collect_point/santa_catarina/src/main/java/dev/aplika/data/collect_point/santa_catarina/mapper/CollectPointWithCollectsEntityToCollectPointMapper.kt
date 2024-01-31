package dev.aplika.data.collect_point.santa_catarina.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.SantaCatarinaCollectPointWithCollectsEntity
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaCollectPoint
import javax.inject.Inject

class CollectPointWithCollectsEntityToCollectPointMapper @Inject constructor(
    private val collectEntityToCollectMapper: CollectEntityToCollectMapper
) : Mapper<SantaCatarinaCollectPointWithCollectsEntity, SantaCatarinaCollectPoint> {
    override fun map(input: SantaCatarinaCollectPointWithCollectsEntity): SantaCatarinaCollectPoint {
        return SantaCatarinaCollectPoint(
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