package com.aplika.core.data.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.domain.model.BeachCollect
import com.aplika.core.network.model.BeachCollectDto
import javax.inject.Inject

class BeachCollectDtoToBeachCollectMapper @Inject constructor(
    private val collectDtoToCollectMapper: CollectDtoToCollectMapper
) : Mapper<BeachCollectDto, BeachCollect> {
    override fun map(input: BeachCollectDto): BeachCollect {
        return BeachCollect(
            id = input.cityId,
            cityIbgeId = input.cityIbgeId,
            city = input.city,
            collectPoint = input.collectPoint,
            beach = input.beach,
            location = input.location,
            latitude = input.latitude.toDouble(),
            longitude = input.longitude.toDouble(),
            collects = input.collects.mapNotNull { collectDtoToCollectMapper.map(input = it) }
        )
    }
}