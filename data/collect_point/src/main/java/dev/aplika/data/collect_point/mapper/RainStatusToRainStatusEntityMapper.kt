package dev.aplika.data.collect_point.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.BathabilityStatusEntity
import dev.aplika.core.database.model.RainStatusEntity
import dev.aplika.core.domain.model.BathabilityStatus
import dev.aplika.core.domain.model.RainStatus
import javax.inject.Inject

class RainStatusToRainStatusEntityMapper @Inject constructor() : Mapper<RainStatus, RainStatusEntity> {
    override fun map(input: RainStatus): RainStatusEntity {
        return when(input) {
            RainStatus.ABSENT -> RainStatusEntity.ABSENT
            RainStatus.WEAK -> RainStatusEntity.WEAK
            RainStatus.MODERATE -> RainStatusEntity.MODERATE
            RainStatus.UNKNOWN -> RainStatusEntity.UNKNOWN
        }
    }
}