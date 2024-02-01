package dev.aplika.data.collect_point.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.data.mapper.CollectPointIdToCollectPointIdEntityMapper
import dev.aplika.core.database.model.CollectPointEntity
import dev.aplika.core.domain.model.CollectPoint
import javax.inject.Inject

class CollectPointToCollectPointEntityMapper @Inject constructor(
    private val collectPointIdToCollectPointIdEntityMapper: CollectPointIdToCollectPointIdEntityMapper,
    private val bathabilityStatusToBathabilityStatusEntityMapper: BathabilityStatusToBathabilityStatusEntityMapper
) : Mapper<CollectPoint, CollectPointEntity> {
    override fun map(input: CollectPoint): CollectPointEntity {
        return CollectPointEntity(
            id = collectPointIdToCollectPointIdEntityMapper.map(input = input.id),
            latitude = input.latitude,
            longitude = input.longitude,
            latestBathabilityStatus = bathabilityStatusToBathabilityStatusEntityMapper.map(input = input.latestBathabilityStatus)
        )
    }
}