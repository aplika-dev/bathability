package com.aplika.core.data.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.database.model.CollectEntity
import com.aplika.core.domain.model.BathabilitySituation
import com.aplika.core.domain.model.Collect
import com.aplika.core.domain.model.RainSituation
import com.aplika.core.kotlin.extensions.toDate
import java.util.Date
import javax.inject.Inject

class CollectEntityToCollectMapper @Inject constructor() : Mapper<CollectEntity, Collect> {
    override fun map(input: CollectEntity): Collect {
        return Collect(
            date = Date(input.date),
            bathabilitySituation = BathabilitySituation.getById(id = input.bathabilitySituation),
            rainSituation = RainSituation.getById(id = input.rainSituation),
            escherichiaColiFactor = input.escherichiaColiFactor
        )
    }
}