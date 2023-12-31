package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.BeachWithCollectsEntity
import dev.aplika.core.domain.model.BeachCollect
import javax.inject.Inject

class BeachWithCollectsEntityToBeachCollectMapper @Inject constructor(
    private val collectEntityToCollectMapper: CollectEntityToCollectMapper
) : Mapper<BeachWithCollectsEntity, BeachCollect> {
    override fun map(input: BeachWithCollectsEntity): BeachCollect {
        return BeachCollect(
            id = input.beach.id,
            cityIbgeId = input.beach.cityIbgeId,
            city = input.beach.city,
            collectPoint = input.beach.collectPoint,
            beach = input.beach.beach,
            location = input.beach.location,
            latitude = input.beach.latitude,
            longitude = input.beach.longitude,
            collects = input.collects.map { collectEntityToCollectMapper.map(input = it) }.sortedByDescending { it.date }
        )
    }
}