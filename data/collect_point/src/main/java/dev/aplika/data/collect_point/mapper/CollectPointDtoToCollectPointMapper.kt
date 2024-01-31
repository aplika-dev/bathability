package dev.aplika.data.collect_point.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.network.santa_catarina.model.CollectPointDto
import javax.inject.Inject

class CollectPointDtoToCollectPointMapper @Inject constructor(
    private val collectDtoToCollectMapper: CollectDtoToCollectMapper
) : Mapper<CollectPointDto, CollectPoint> {
    override fun map(input: CollectPointDto): CollectPoint {
        return CollectPoint(
            id = input.cityId,
            city = input.city,
            name = input.beach,
            description = "${input.location} (${input.collectPoint})",
            latitude = input.latitude.toDouble(),
            longitude = input.longitude.toDouble(),
            collects = input.collects.orEmpty().mapNotNull { collectDtoToCollectMapper.map(input = it) }
        )
    }
}