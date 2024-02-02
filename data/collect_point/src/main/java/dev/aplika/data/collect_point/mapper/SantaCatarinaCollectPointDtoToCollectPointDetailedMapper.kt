package dev.aplika.data.collect_point.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.core.domain.model.CollectPointDetailed
import dev.aplika.core.domain.model.LocalityGroup
import dev.aplika.network.santa_catarina.model.CollectPointDto
import javax.inject.Inject

class SantaCatarinaCollectPointDtoToCollectPointDetailedMapper @Inject constructor(
    private val santaCatarinaCollectDtoToCollectMapper: SantaCatarinaCollectDtoToCollectMapper
) : Mapper<CollectPointDto, CollectPointDetailed> {
    override fun map(input: CollectPointDto): CollectPointDetailed {
        return CollectPointDetailed(
            id = input.cityId,
            localityGroup = LocalityGroup.SANTA_CATARINA,
            name = "${input.beach} - ${input.location}",
            city = input.city,
            latitude = input.latitude.toDouble(),
            longitude = input.longitude.toDouble(),
            latestCollects = input.collects.orEmpty().mapNotNull { santaCatarinaCollectDtoToCollectMapper.map(input = it) }
        )
    }
}