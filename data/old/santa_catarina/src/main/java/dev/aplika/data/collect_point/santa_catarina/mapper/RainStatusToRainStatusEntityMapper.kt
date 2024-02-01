package dev.aplika.data.collect_point.santa_catarina.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.RainStatusEntity
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaRainStatus
import javax.inject.Inject

class RainStatusToRainStatusEntityMapper @Inject constructor() : Mapper<SantaCatarinaRainStatus, RainStatusEntity> {
    override fun map(input: SantaCatarinaRainStatus): RainStatusEntity {
        return when(input) {
            SantaCatarinaRainStatus.ABSENT -> RainStatusEntity.ABSENT
            SantaCatarinaRainStatus.WEAK -> RainStatusEntity.WEAK
            SantaCatarinaRainStatus.MODERATE -> RainStatusEntity.MODERATE
            SantaCatarinaRainStatus.UNKNOWN -> RainStatusEntity.UNKNOWN
        }
    }
}