package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.CollectEntity
import dev.aplika.core.domain.model.Collect
import javax.inject.Inject

class CollectWithBeachIdToCollectEntityMapper @Inject constructor() : Mapper<Pair<Collect, String>, CollectEntity> {
    override fun map(input: Pair<Collect, String>): CollectEntity {
        return CollectEntity(
            date = input.first.date.time,
            bathabilitySituation = input.first.bathabilityStatus.id,
            rainSituation = input.first.rainStatus.id,
            escherichiaColiFactor = input.first.escherichiaColiFactor,
            beachId = input.second
        )
    }
}