package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.CollectEntity
import dev.aplika.core.domain.model.Collect
import dev.aplika.core.kotlin.extensions.formatToString
import java.util.Date
import javax.inject.Inject

class CollectWithBeachIdToCollectEntityMapper @Inject constructor() : Mapper<Pair<Collect, String>, CollectEntity> {
    override fun map(input: Pair<Collect, String>): CollectEntity {
        return CollectEntity(
            date = input.first.date.time,
            bathabilitySituation = input.first.bathabilitySituation.id,
            rainSituation = input.first.rainSituation.id,
            escherichiaColiFactor = input.first.escherichiaColiFactor,
            beachId = input.second
        )
    }
}