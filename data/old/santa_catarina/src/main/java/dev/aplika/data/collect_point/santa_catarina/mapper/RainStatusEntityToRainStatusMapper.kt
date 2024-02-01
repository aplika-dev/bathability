package dev.aplika.data.collect_point.santa_catarina.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.RainStatusEntity
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaRainStatus
import javax.inject.Inject

class RainStatusEntityToRainStatusMapper @Inject constructor() : Mapper<RainStatusEntity, SantaCatarinaRainStatus> {
    override fun map(input: RainStatusEntity): SantaCatarinaRainStatus {
        return when(input) {
            RainStatusEntity.ABSENT -> SantaCatarinaRainStatus.ABSENT
            RainStatusEntity.WEAK -> SantaCatarinaRainStatus.WEAK
            RainStatusEntity.MODERATE -> SantaCatarinaRainStatus.MODERATE
            RainStatusEntity.UNKNOWN -> SantaCatarinaRainStatus.UNKNOWN
        }
    }
}