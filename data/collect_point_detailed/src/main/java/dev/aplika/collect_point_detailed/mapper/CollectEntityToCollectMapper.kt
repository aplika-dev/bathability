package dev.aplika.collect_point_detailed.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.CollectEntity
import dev.aplika.core.domain.model.Collect
import java.util.Date
import javax.inject.Inject

class CollectEntityToCollectMapper @Inject constructor() : Mapper<CollectEntity, Collect> {
    override fun map(input: CollectEntity): Collect {
        return Collect(
            date = Date(input.date),
            bathabilityStatus = input.bathabilityStatus,
            rainStatus = input.rainStatus,
            escherichiaColiFactor = input.escherichiaColiFactor
        )
    }
}