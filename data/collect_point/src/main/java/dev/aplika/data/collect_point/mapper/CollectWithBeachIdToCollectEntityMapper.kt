package dev.aplika.data.collect_point.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.BathabilityStatusEntity
import dev.aplika.core.database.model.CollectEntity
import dev.aplika.core.domain.model.BathabilityStatus
import dev.aplika.core.domain.model.Collect
import javax.inject.Inject

class CollectWithBeachIdToCollectEntityMapper @Inject constructor(
    private val bathabilityStatusToBathabilityStatusEntityMapper: BathabilityStatusToBathabilityStatusEntityMapper,
    private val rainStatusToRainStatusEntityMapper: RainStatusToRainStatusEntityMapper
) : Mapper<Pair<Collect, String>, CollectEntity> {
    override fun map(input: Pair<Collect, String>): CollectEntity {
        return CollectEntity(
            collectPointId = input.second,
            date = input.first.date.time,
            bathabilityStatus = bathabilityStatusToBathabilityStatusEntityMapper.map(input = input.first.bathabilityStatus),
            rainStatus = input.first.rainStatus?.let { rainStatusToRainStatusEntityMapper.map(input = it) },
            escherichiaColiFactor = input.first.escherichiaColiFactor
        )
    }
}