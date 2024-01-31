package dev.aplika.data.collect_point.santa_catarina.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.SantaCatarinaCollectEntity
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaCollect
import javax.inject.Inject

class CollectWithBeachIdToCollectEntityMapper @Inject constructor(
    private val bathabilityStatusToBathabilityStatusEntityMapper: BathabilityStatusToBathabilityStatusEntityMapper,
    private val rainStatusToRainStatusEntityMapper: RainStatusToRainStatusEntityMapper
) : Mapper<Pair<SantaCatarinaCollect, String>, SantaCatarinaCollectEntity> {
    override fun map(input: Pair<SantaCatarinaCollect, String>): SantaCatarinaCollectEntity {
        return SantaCatarinaCollectEntity(
            collectPointId = input.second,
            date = input.first.date.time,
            bathabilityStatus = bathabilityStatusToBathabilityStatusEntityMapper.map(input = input.first.bathabilityStatus),
            rainStatus = rainStatusToRainStatusEntityMapper.map(input = input.first.rainStatus),
            escherichiaColiFactor = input.first.escherichiaColiFactor
        )
    }
}