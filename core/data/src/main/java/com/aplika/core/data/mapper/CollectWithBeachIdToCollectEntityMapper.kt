package com.aplika.core.data.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.database.model.CollectEntity
import com.aplika.core.domain.model.Collect
import com.aplika.core.kotlin.extensions.formatToString
import javax.inject.Inject

class CollectWithBeachIdToCollectEntityMapper @Inject constructor() : Mapper<Pair<Collect, String>, CollectEntity> {
    override fun map(input: Pair<Collect, String>): CollectEntity {
        return CollectEntity(
            date = input.first.date.formatToString(format = "dd/MM/yyyy"),
            bathabilitySituation = input.first.bathabilitySituation.id,
            rainSituation = input.first.rainSituation.id,
            escherichiaColiFactor = input.first.escherichiaColiFactor,
            beachId = input.second
        )
    }
}