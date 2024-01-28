package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.core.network.model.BeachCollectDto
import javax.inject.Inject

class BeachCollectDtoToBeachCollectMapper @Inject constructor(
    private val collectDtoToCollectMapper: CollectDtoToCollectMapper
) : Mapper<BeachCollectDto, CollectPoint> {
    override fun map(input: BeachCollectDto): CollectPoint {
        return CollectPoint(
            id = input.cityId,
            cityIbgeId = input.cityIbgeId,
            city = input.city,
            collectPoint = input.collectPoint,
            beach = input.beach,
            location = input.location,
            latitude = input.latitude.toDouble(),
            longitude = input.longitude.toDouble(),
            collects = input.collects.orEmpty().mapNotNull { collectDtoToCollectMapper.map(input = it) }
        )
    }
}