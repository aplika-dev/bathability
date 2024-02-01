package dev.aplika.data.collect_point.santa_catarina.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.CollectEntity
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaCollect
import java.util.Date
import javax.inject.Inject

class CollectEntityToCollectMapper @Inject constructor(
    private val bathabilityStatusEntityToBathabilityStatusMapper: BathabilityStatusEntityToBathabilityStatusMapper,
    private val rainStatusEntityToRainStatusMapper: RainStatusEntityToRainStatusMapper
) : Mapper<CollectEntity, SantaCatarinaCollect> {
    override fun map(input: CollectEntity): SantaCatarinaCollect {
        return SantaCatarinaCollect(
            date = Date(input.date),
            bathabilityStatus = bathabilityStatusEntityToBathabilityStatusMapper.map(input = input.bathabilityStatus),
            rainStatus = rainStatusEntityToRainStatusMapper.map(input = input.rainStatus),
            escherichiaColiFactor = input.escherichiaColiFactor
        )
    }
}