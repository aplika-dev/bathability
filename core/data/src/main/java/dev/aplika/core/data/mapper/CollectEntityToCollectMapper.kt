package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.CollectEntity
import dev.aplika.core.domain.model.BathabilityStatus
import dev.aplika.core.domain.model.Collect
import dev.aplika.core.domain.model.RainStatus
import java.util.Date
import javax.inject.Inject

class CollectEntityToCollectMapper @Inject constructor() : Mapper<CollectEntity, Collect> {
    override fun map(input: CollectEntity): Collect {
        return Collect(
            date = Date(input.date),
            bathabilityStatus = BathabilityStatus.getById(id = input.bathabilitySituation),
            rainStatus = RainStatus.getById(id = input.rainSituation),
            escherichiaColiFactor = input.escherichiaColiFactor
        )
    }
}