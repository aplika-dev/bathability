package dev.aplika.collect_point_detailed.mapper

import dev.aplika.core.android.mapper.Mapper
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