package dev.aplika.data.collect_point.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.BathabilityStatusEntity
import dev.aplika.core.database.model.RainStatusEntity
import dev.aplika.core.domain.model.BathabilityStatus
import dev.aplika.core.domain.model.RainStatus
import javax.inject.Inject

class RainStatusEntityToRainStatusMapper @Inject constructor() : Mapper<RainStatusEntity?, RainStatus> {
    override fun map(input: RainStatusEntity?): RainStatus {
        return when(input) {
            RainStatusEntity.ABSENT -> RainStatus.ABSENT
            RainStatusEntity.WEAK -> RainStatus.WEAK
            RainStatusEntity.MODERATE -> RainStatus.MODERATE
            RainStatusEntity.UNKNOWN -> RainStatus.UNKNOWN
            null -> RainStatus.UNKNOWN
        }
    }
}