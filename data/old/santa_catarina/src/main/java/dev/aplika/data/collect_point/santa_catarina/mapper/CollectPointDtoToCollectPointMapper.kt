package dev.aplika.data.collect_point.santa_catarina.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaCollectPoint
import dev.aplika.data.collect_point.santa_catarina.model.SantaCatarinaCollectPointDto
import javax.inject.Inject

class CollectPointDtoToCollectPointMapper @Inject constructor(
    private val collectDtoToCollectMapper: CollectDtoToCollectMapper
) : Mapper<SantaCatarinaCollectPointDto, SantaCatarinaCollectPoint> {
    override fun map(input: SantaCatarinaCollectPointDto): SantaCatarinaCollectPoint {
        return SantaCatarinaCollectPoint(
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