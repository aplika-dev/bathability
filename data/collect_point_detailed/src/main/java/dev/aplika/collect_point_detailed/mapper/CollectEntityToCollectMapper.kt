package dev.aplika.collect_point_detailed.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.data.mapper.BathabilityStatusEntityToBathabilityStatusMapper
import dev.aplika.core.database.model.CollectEntity
import dev.aplika.core.domain.model.Collect
import java.util.Date
import javax.inject.Inject

class CollectEntityToCollectMapper @Inject constructor(
    private val bathabilityStatusEntityToBathabilityStatusMapper: BathabilityStatusEntityToBathabilityStatusMapper,
    private val rainStatusEntityToRainStatusMapper: RainStatusEntityToRainStatusMapper
) : Mapper<CollectEntity, Collect> {
    override fun map(input: CollectEntity): Collect {
        return Collect(
            date = Date(input.date),
            bathabilityStatus = bathabilityStatusEntityToBathabilityStatusMapper.map(input = input.bathabilityStatus),
            rainStatus = input.rainStatus?.let { rainStatusEntityToRainStatusMapper.map(input = it) },
            escherichiaColiFactor = input.escherichiaColiFactor
        )
    }
}