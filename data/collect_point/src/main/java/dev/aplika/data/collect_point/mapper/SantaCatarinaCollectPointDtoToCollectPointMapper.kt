package dev.aplika.data.collect_point.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.core.domain.model.CollectPointId
import dev.aplika.core.domain.model.LocalityGroup
import dev.aplika.network.santa_catarina.model.CollectPointDto
import javax.inject.Inject

class SantaCatarinaCollectPointDtoToCollectPointMapper @Inject constructor(
    private val santaCatarinaBathabilityStatusStringDtoToBathabilityStatusMapper: SantaCatarinaBathabilityStatusStringDtoToBathabilityStatusMapper
) : Mapper<CollectPointDto, CollectPoint> {
    override fun map(input: CollectPointDto): CollectPoint {
        return CollectPoint(
            id = CollectPointId(
                id = input.cityId,
                localityGroup = LocalityGroup.SANTA_CATARINA
            ),
            latitude = input.latitude.toDouble(),
            longitude = input.longitude.toDouble(),
            latestBathabilityStatus = santaCatarinaBathabilityStatusStringDtoToBathabilityStatusMapper.map(input = input.collects?.firstOrNull()?.bathabilityStatus)
        )
    }
}