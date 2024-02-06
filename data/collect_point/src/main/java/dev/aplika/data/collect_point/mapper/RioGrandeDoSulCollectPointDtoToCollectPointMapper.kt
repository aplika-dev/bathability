package dev.aplika.data.collect_point.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.data.mapper.RioGrandeDoSulBathabilityStatusCodeDtoToBathabilityStatusMapper
import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.core.domain.model.LocalityGroup
import dev.aplika.network.rio_grande_do_sul.model.CollectPointDto
import javax.inject.Inject

class RioGrandeDoSulCollectPointDtoToCollectPointMapper @Inject constructor(
    private val rioGrandeDoSulBathabilityStatusCodeDtoToBathabilityStatusMapper: RioGrandeDoSulBathabilityStatusCodeDtoToBathabilityStatusMapper
) : Mapper<CollectPointDto, CollectPoint> {
    override fun map(input: CollectPointDto): CollectPoint {
        return CollectPoint(
            id = input.id.toString(),
            localityGroup = LocalityGroup.RIO_GRANDE_DO_SUL,
            name = input.name,
            city = input.cityName,
            latitude = input.latitude.toDouble(),
            longitude = input.longitude.toDouble(),
            latestBathabilityStatus = rioGrandeDoSulBathabilityStatusCodeDtoToBathabilityStatusMapper.map(input = input.status)
        )
    }
}